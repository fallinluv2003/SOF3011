package com.example.Assignment.repository;

import com.example.Assignment.entity.MauSac;
import com.example.Assignment.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MauSacRepository {
    public List<MauSac> getAll() {
        List<MauSac> list = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM MauSac", MauSac.class);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public MauSac getOne(String id) {
        MauSac category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM MauSac WHERE id=:id", MauSac.class);
            query.setParameter("id", id);
            category = (MauSac) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public MauSac getName(String ten) {
        MauSac category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM MauSac WHERE ten=:ten", MauSac.class);
            query.setParameter("ten", ten);
            category = (MauSac) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public Boolean add(MauSac ms) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.persist(ms);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean update(MauSac ms) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.merge(ms);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(MauSac ms) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.delete(ms);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
}
