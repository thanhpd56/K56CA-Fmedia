<?php
    
    require_once '../Controller/linkController.php';
    $linkControl = new linkController();
    
?>
<nav class="navbar navbar-inverse" role="navigation" style="margin: 0px;">
    <ul class="nav navbar-nav">
        <li><a href="index.php">FMEDIA CONTROL PANEL</a></li>
        <li class="active"><a href="index.php">Manage Video</a></li>
        <li><a href="manageCategory.php">Manage Category</a></li>
        <li><a href="insertLink.php">Insert Video</a></li>
    </ul>
    <form class="navbar-form navbar-right" role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search" name="keyword">
        </div>
        <button type="button" class="btn btn-default"
                onclick="searchKey('search.php?key='+keyword.value)"
                >Search</button>
    </form>
    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><?php echo getfield('name');?> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="../webpages/changPassword.php">Setting</a></li>
                <li><a href="../webpages/logout.php">Logout</a></li>
                
            </ul>
        </li>
    </ul>
</nav>












