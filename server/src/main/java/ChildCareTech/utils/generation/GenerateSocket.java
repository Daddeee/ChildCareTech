package ChildCareTech.utils.generation;

import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.controller.*;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class GenerateSocket {
    public static void main(String [] args) throws Exception{
        Method[] methods = UserSessionFacade.class.getDeclaredMethods();
        HashMap<String, String> methodMap = new HashMap<>();
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";

        PrintWriter metodi = new PrintWriter("src/main/java/ChildCareTech/utils/generation/metodi", "UTF-8");

        Map<String, Method> controllerMethods = new HashMap<>();
        for(Method m : AdultController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : BusController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : CanteenController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : CheckpointController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : DishController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : FoodController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : KidController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : MenuController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : PediatristController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : PersonController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : RouteController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : StaffController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : SupplierController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : TripController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : TripPartecipationController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : WorkDayController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);
        for(Method m : WorkDayGenerationController.class.getDeclaredMethods())
            controllerMethods.put(m.getName(), m);

        for(Method m : methods){
            if(m.getName().equals("logout")) continue;
            String snakeCaseMethodName = m.getName()
                    .replaceAll(regex, replacement)
                    .toUpperCase();

            String handleMethodName = "handle" + m.getName().substring(0, 1).toUpperCase() + m.getName().substring(1);
            methodMap.put(snakeCaseMethodName, handleMethodName);

            String returnType = m.getReturnType().getSimpleName();
            String serializableCast = "";
            if(returnType.equals("List") || returnType.equals("Collection") || returnType.equals("Set")) {
                returnType = m.getGenericReturnType().getTypeName();
                serializableCast = "(Serializable) ";
            }

            String eventualReturn = returnType.equals("void") ? "\n\n" : "\t" + returnType + " returnValue;\n\n";

            String returnVariable = returnType.equals("void") ? "null" : "returnValue";

            String assignReturnVariable = returnType.equals("void") ? "" : "returnValue = ";

            Method controllerMethod = controllerMethods.get("do" + m.getName().substring(0, 1).toUpperCase() + m.getName().substring(1));

            String callingInstance = "";
            try {
                callingInstance = controllerMethod.getDeclaringClass().getSimpleName().substring(0, 1).toLowerCase() + controllerMethod.getDeclaringClass().getSimpleName().substring(1);
            } catch (Exception e){
                e.printStackTrace();
            }

            String methodString = assignReturnVariable + callingInstance + "." + controllerMethod.getName() + "(";
            for(int i = 0; i < m.getParameters().length - 1; i++)
                methodString += m.getParameters()[i].getName() + ", ";
            if(m.getParameters().length > 0)
                methodString += m.getParameters()[m.getParameters().length - 1].getName();
            methodString += ");";

            metodi.write(
                    "private SocketResponse " + handleMethodName + "(SocketRequest request) {\n" +
                            "\tSocketResponse response;\n" +
                            eventualReturn +
                            "\ttry{\n"
            );

            int i = 0;
            for(Parameter p : m.getParameters()){
                metodi.write("\t\t" + p.getType().getSimpleName() + " " + p.getName() + " = (" + p.getType().getSimpleName() + ") " + "request.params[" + i + "]" + ";\n");
                i++;
            }

            metodi.write("\n\t\t" + methodString + "\n");
            metodi.write("\t\tresponse = new SocketResponse(SocketResponseType.SUCCESS, " + serializableCast + returnVariable + ");\n");
            metodi.write("\t} catch(Exception e) {\n");
            metodi.write("\t\tresponse = new SocketResponse(SocketResponseType.FAIL, e);\n");
            metodi.write("\t}\n\n\treturn response;\n}\n\n");
        }

        for(Map.Entry<String, String> entry : methodMap.entrySet()) {
            metodi.write("this.methodMap.put(SocketRequestType." + entry.getKey() + ", this:: " + entry.getValue() + ");\n");
        }

        metodi.close();
    }
}
