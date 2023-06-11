package com.example.Assignment.repository;

import com.example.Assignment.entity.DongSanPham;
import com.example.Assignment.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DongSanPhamRepository {
    public List<DongSanPham> getAll() {
        List<DongSanPham> list = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM DongSanPham", DongSanPham.class);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public DongSanPham getOne(String id) {
        DongSanPham category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM DongSanPham WHERE id=:id", DongSanPham.class);
            query.setParameter("id", id);
            category = (DongSanPham) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public DongSanPham getName(String ten) {
        DongSanPham category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM DongSanPham WHERE ten=:ten", DongSanPham.class);
            query.setParameter("ten", ten);
            category = (DongSanPham) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public Boolean add(DongSanPham dongSanPham) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.persist(dongSanPham);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean update(DongSanPham dongSanPham) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.merge(dongSanPham);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(DongSanPham dongSanPham) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.delete(dongSanPham);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
}
