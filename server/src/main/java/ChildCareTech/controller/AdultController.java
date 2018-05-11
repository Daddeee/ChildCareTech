package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdultController {
    public AdultController() {}

    public void doSaveAdult(AdultDTO adultDTO) throws AddFailedException {
        AdultDAO adultDAO = new AdultDAO();
        PersonDAO personDAO = new PersonDAO();
        Adult adult = DTOEntityAssembler.getEntity(adultDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", adultDTO.getPerson().getFiscalCode());

        adultDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                adultDAO.create(adult);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente");

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<AdultDTO> doGetAllAdults() {
        AdultDAO adultDAO = new AdultDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<AdultDTO> adultDTOList = new ArrayList<>();
        List<Adult> adultList = new ArrayList<>();
        Transaction tx = null;
        adultDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            adultList = adultDAO.readAll();
            for(Adult adult : adultList)
                adultDAO.initializeLazyRelations(adult);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Adult adult : adultList) {
            adultDTOList.add(DTOFactory.getDTO(adult));
        }
        return adultDTOList;
    }

    public void doRemoveAdult(AdultDTO adultDTO) {
        AdultDAO adultDAO = new AdultDAO();
        Adult adult = DTOEntityAssembler.getEntity(adultDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        adultDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            adultDAO.delete(adult);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
