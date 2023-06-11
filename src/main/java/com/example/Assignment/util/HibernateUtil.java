package com.example.Assignment.util;

import com.example.Assignment.entity.ChiTietSanPham;
import com.example.Assignment.entity.ChucVu;
import com.example.Assignment.entity.CuaHang;
import com.example.Assignment.entity.DongSanPham;
import com.example.Assignment.entity.KhachHang;
import com.example.Assignment.entity.MauSac;
import com.example.Assignment.entity.NhaSanXuat;
import com.example.Assignment.entity.NhanVien;
import com.example.Assignment.entity.SanPham;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * @author hangnt169
 */

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=AssignmentJava4");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(CuaHang.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(DongSanPham.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NhaSanXuat.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(ChiTietSanPham.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}
