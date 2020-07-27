<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>MoviePick</title>

        <!--    Google Fonts-->
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!--Fontawesom-->
        <link rel="stylesheet" href="../css/font-awesome.min.css">

        <!--Animated CSS-->
        <link rel="stylesheet" type="text/css" href="../css/animate.min.css">

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!--Bootstrap Carousel-->
        <link type="text/css" rel="stylesheet" href="../css/carousel.css" />

        <link rel="stylesheet" href="../css/isotope/style.css">

        <!--Main Stylesheet-->
        <link href="../css/style.css" rel="stylesheet">
        <!--Responsive Framework-->
        <link href="../css/responsive.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
         <script src="../js/jquery-1.12.3.min.js"></script>
<style>
#sub-menu{
   margin: 0;
   padding: 0;
   list-style-type: none;
   opacity: 0;
   visibility: hidden;
}
.nav > li:hover #sub-menu {
opacity: 1;
visibility: visible;
}
</style>
    </head>


	
    <body data-spy="scroll" data-target="#header">
	   <!--Start Header Section--> 
        <section id="header" style="background-color: black;">
            <div class="header-area">

                <!--End of top header-->
                <div class="header_menu text-center" data-spy="affix" data-offset-top="50" id="nav">
                    <div class="container">
                        <nav class="navbar navbar-default zero_mp ">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
 
                            <c:if test="${empty uid or uid ne 'admin'}">
                                <a class="navbar-brand custom_navbar-brand" href="../main/index.do"><img src="../img/logo1.png" alt=""></a>
                                </c:if>
                                </div>
                            <!--End of navbar-header-->

                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse zero_mp" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav navbar-right main_menu">
                                    
                                              <c:if test="${empty uid}">  
                                    <li><a href="../intro/index.do">intro</a></li>
                                    <li><a href="../movie/list.do">MoviePick</a> </li>
                                    <li><a href="../bbs/list.do">UserPick</a></li>                                   
                                    <li><a href="../notice/list.do ">Notice</a></li>              
                                    <li><a href="../login/login.do">login</a></li>
                                    </c:if>       
                                    <c:if test="${!empty uid and uid eq 'admin' }">
                                    <li><a href="../admin/review.do">댓글관리</a></li>
                                    <li><a href="../admin/movie.do">무비픽관리</a></li>
                                    <li><a href="../admin/bbslist.do">유저픽관리</a></li>
                                    <li><a href="../admin/notice.do">공지사항관리</a></li>
                                    <li><a href="../admin/login.do">회원관리</a></li>
                                    <li><a href="../admin/screen.do">상영관리</a></li>
                                    <li><a href="../admin/people.do">인물정보관리</a></li>
                                    <li><a href="../login/logout.do">logout</a></li>
                                    </c:if>                   
                                    <c:if test="${!empty uid and uid ne 'admin'}">
                                    <li><a href="../intro/index.do">intro</a></li>
                                    <li><a href="../movie/list.do">MoviePick</a></li>
                                    <li><a href="../bbs/list.do">UserPick</a></li>                                   
                                    <li><a href="../notice/list.do ">Notice</a></li>
                                    <li><a href="../movie/choicetiket.do">Ticketing</a></li>
                                    <li><a href="../mypage/mypage.do">Mypage</a></li>
                                    <li><a href="../login/logout.do">logout</a></li>
                                    </c:if>
                                    
                                </ul>
                            </div>
                            <!-- /.navbar-collapse -->
                        </nav>
                        <!--End of nav-->
                    </div>
                    <!--End of container-->
                </div>
                <!--End of header menu-->
            </div>
            <!--end of header area-->
        </section>
        <!--End of Hedaer Section-->

