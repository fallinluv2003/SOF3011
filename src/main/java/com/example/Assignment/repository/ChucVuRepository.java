package com.example.Assignment.repository;

import com.example.Assignment.entity.ChucVu;
import com.example.Assignment.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ChucVuRepository {
    public List<ChucVu> getAll() {
        List<ChucVu> list = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChucVu", ChucVu.class);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public ChucVu getOne(String id) {
        ChucVu category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChucVu WHERE id=:id", ChucVu.class);
            query.setParameter("id", id);
            category = (ChucVu) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public ChucVu getName(String ten) {
        ChucVu category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChucVu WHERE ten=:ten", ChucVu.class);
            query.setParameter("ten", ten);
            category = (ChucVu) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public Boolean add(ChucVu cv) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.persist(cv);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean update(ChucVu cv) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.merge(cv);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(ChucVu cv) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.delete(cv);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
}
