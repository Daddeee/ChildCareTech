package ChildCareTech.utils.generation;

import ChildCareTech.common.UserSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class GenerateSocket {
    public static void main(String [] args) throws IOException {
        String simpleNameRegexp = "(?<=\\.)(?=[^.]*$).*";
        Method[] methods = UserSession.class.getDeclaredMethods();
        PrintWriter metodi = new PrintWriter("src/main/java/ChildCareTech/utils/generation/metodi", "UTF-8");

        for(Method m : methods){
            metodi.write("@Override\n");
            String access = "public";

            String returnType = m.getGenericReturnType().getTypeName();

            String paramString = "";
            String callingParamString = "";
            for(Parameter p : m.getParameters()){
                paramString += p.getType().getSimpleName() + " " + p.getName() + ", ";
                callingParamString += p.getName() + ", ";
            }
            if(m.getParameters().length > 0) {
                paramString = paramString.substring(0, paramString.length() - 2);
                callingParamString = ", " + callingParamString.substring(0, callingParamString.length() - 2);
            }

            String mainThrowingCast = "(RemoteException)";
            String throwingString = "";
            if(m.getExceptionTypes().length > 0){
                throwingString = "throws ";
                for(Class c : m.getExceptionTypes()){
                    throwingString += c.getSimpleName() + ", ";
                    if(!c.getSimpleName().equals("RemoteException"))
                        mainThrowingCast = "(" + c.getSimpleName() + ")";
                }

                throwingString = throwingString.substring(0, throwingString.length() - 2);
                throwingString += " ";
            }



            String methodSignature = access + " " + returnType + " " + m.getName() + "(" + paramString + ") " + throwingString;
            String regex = "([a-z])([A-Z]+)";
            String replacement = "$1_$2";
            String snakeCaseMethodName = m.getName()
                    .replaceAll(regex, replacement)
                    .toUpperCase();

            String returnCast = "(" + returnType + ")";
            String returnString = m.getReturnType().getSimpleName().equals("void") ? "" : "return " + returnCast + " response.returnValue;";

            metodi.write(methodSignature + "{\n");
            metodi.write("\tSocketRequest request = new SocketRequest(SocketRequestType." + snakeCaseMethodName + callingParamString + ");\n");
            metodi.write("\tSocketResponse response;\n\n");
            metodi.write("\ttry{\n");
            metodi.write("\t\tout.writeObject(request);\n");
            metodi.write("\t\tresponse = (SocketResponse)in.readObject();\n");
            metodi.write("\t} catch(IOException | ClassNotFoundException e) {\n");
            metodi.write("\t\te.printStackTrace();\n");
            metodi.write("\t\tthrow new RemoteException(e.getMessage());\n");
            metodi.write("\t}\n\n");
            metodi.write("\tif(response.responseType.equals(SocketResponseType.FAIL))\n");
            metodi.write("\t\tthrow " + mainThrowingCast + " response.returnValue;\n");
            metodi.write("\t" + returnString + "\n");
            metodi.write("}\n\n");
        }

        metodi.close();
    }
}
