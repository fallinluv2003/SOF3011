package com.example.Assignment.repository;

import com.example.Assignment.entity.CuaHang;
import com.example.Assignment.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CuaHangRepository {
    public List<CuaHang> getAll() {
        List<CuaHang> list = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM CuaHang", CuaHang.class);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public CuaHang getOne(String id) {
        CuaHang category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM CuaHang WHERE id=:id", CuaHang.class);
            query.setParameter("id", id);
            category = (CuaHang) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public CuaHang getName(String ten) {
        CuaHang category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM CuaHang WHERE ten=:ten", CuaHang.class);
            query.setParameter("ten", ten);
            category = (CuaHang) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public Boolean add(CuaHang ch) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.persist(ch);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean update(CuaHang ch) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.merge(ch);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(CuaHang ch) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.delete(ch);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
}
