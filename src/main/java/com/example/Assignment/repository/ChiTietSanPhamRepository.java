package com.example.Assignment.repository;

import com.example.Assignment.entity.ChiTietSanPham;
import com.example.Assignment.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamRepository {

    public List<ChiTietSanPham> getAll() {
        List<ChiTietSanPham> list = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChiTietSanPham", ChiTietSanPham.class);
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public ChiTietSanPham getOne(String id) {
        ChiTietSanPham category = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChiTietSanPham WHERE id=:id", ChiTietSanPham.class);
            query.setParameter("id", id);
            category = (ChiTietSanPham) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return category;
    }

    public Boolean add(ChiTietSanPham ctsp) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.persist(ctsp);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean update(ChiTietSanPham ctsp) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.merge(ctsp);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(ChiTietSanPham ctsp) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            trans = session.beginTransaction();
            session.delete(ctsp);
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ChiTietSanPhamRepository().getAll().toString());
    }

}
