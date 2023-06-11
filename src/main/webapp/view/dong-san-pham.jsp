<%@ page import="jakarta.servlet.http.HttpSession" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 3/25/2023
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
    <title>Bootstap 5 Responsive Admin Dashboard</title>
</head>

<body>
<% HttpSession ses = request.getSession();
    if (ses.getAttribute("message") != null) { %>
<script>
    alert("<%= ses.getAttribute("message") %>");
</script>
<% } %>
<div class="d-flex" id="wrapper">
    <!-- Sidebar -->
    <div class="bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom"><i
                class="fas fa-user-secret me-2"></i>Codersbite
        </div>
        <div class="list-group list-group-flush my-3">
            <a href="/assignment/chi-tiet-san-pham/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text active"><i
                    class="fas fa-tachometer-alt me-2"></i>Chi tiết sản phẩm</a>
            <a href="/assignment/chuc-vu/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                    class="fas fa-project-diagram me-2"></i>Chức vụ</a>
            <a href="/assignment/cua-hang/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                    class="fas fa-chart-line me-2"></i>Cửa hàng</a>
            <a href="/assignment/nhan-vien/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                    class="fas fa-paperclip me-2"></i>Nhân viên</a>
            <a href="/assignment/khach-hang/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                    class="fas fa-shopping-cart me-2"></i>Khách hàng</a>
            <a href="/assignment/mau-sac/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                    class="fas fa-gift me-2"></i>Màu sắc</a>
            <a href="/assignment/nha-san-xuat/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                    class="fas fa-comment-dots me-2"></i>Nhà sản xuất</a>
            <a href="/assignment/dong-san-pham/hien-thi"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                    class="fas fa-map-marker-alt me-2"></i>Dòng Sản Phẩm</a>
            <a href="/assignment/san-pham/hien-thi"
               class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
                    class="fas fa-power-off me-2"></i>Sản phẩm</a>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
            <div class="d-flex align-items-center">
                <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                <h2 class="fs-2 m-0">Dòng sản phẩm</h2>
            </div>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle second-text fw-bold" href="#" id="navbarDropdown"
                           role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user me-2"></i>Bùi Thành Đạt
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container-fluid px-4">
            <form action="/assignment/dong-san-pham/add" method="POST">
                <div class="row">
                    <div class="col-6">
                        <label>Mã: </label>
                        <input type="text" class="form-control" name="ma" value="${dongSP.ma}" required/>
                    </div>
                    <div class="col-6">
                        <label>Tên: </label>
                        <input type="text" class="form-control" name="ten" value="${dongSP.ten}" required/>
                    </div>
                    <div class="row mt-4" style="justify-content: center">
                        <button class="btn btn-success col-1" type="submit">
                            Add
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <div class="container-fluid px-4">
            <div class="row my-3">
                <div class="col">
                    <table class="table bg-white rounded shadow-sm table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Mã</th>
                            <th scope="col">Tên</th>
                            <th scope="col">Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listDongSanPham}" var="dongSanPham">
                            <tr>
                                <td>${dongSanPham.ma}</td>
                                <td>${dongSanPham.ten}</td>
                                <td>
                                    <a href="/assignment/dong-san-pham/detail?id=${dongSanPham.id}" class="btn btn-primary "
                                       tabindex="-1" role="button"
                                       aria-disabled="true">Detail</a>
                                    <button type="button" class="btn btn-success" data-bs-toggle="modal"
                                            data-bs-target="#exampleModal_${dongSanPham.id}">
                                        Update
                                    </button>
                                    <a href="/assignment/dong-san-pham/remove?id=${dongSanPham.id}" class="btn btn-danger "
                                       tabindex="-1" role="button"
                                       aria-disabled="true">Remove</a>
                                </td>
                            </tr>

                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal_${dongSanPham.id}" tabindex="-1"
                                 aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Thông tin</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <form action="/assignment/dong-san-pham/update?id=${dongSanPham.id}" method="POST">
                                            <div class="modal-body">
                                                <label>Mã: </label>
                                                <input type="text" class="form-control" name="ma" value="${dongSanPham.ma}"
                                                       required/>
                                                <br/>
                                                <label>Tên: </label>
                                                <input type="text" class="form-control" name="ten" value="${dongSanPham.ten}"
                                                       required/>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">
                                                        Close
                                                    </button>
                                                    <button class="btn btn-primary">Save</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /#page-content-wrapper -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    var el = document.getElementById("wrapper");
    var toggleButton = document.getElementById("menu-toggle");

    toggleButton.onclick = function () {
        el.classList.toggle("toggled");
    };
</script>
</body>
</html>
