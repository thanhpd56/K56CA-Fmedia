<!-- Sidebar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="../webpages/">Fmedia Controlpanel</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav navbar-right navbar-user">


            <li class="dropdown user-dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><i class="fa fa-power-off"></i> Log Out</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav side-nav">
            <?php
            $counter = 0;
            $isDropdown = false;
            $maxCatCount = 10;
            $lastList = count($allSubCategories);
            if (isset($allSubCategories)) {
                foreach ($allSubCategories as $subCat) {
                    $counter++;

                    if ($counter == $maxCatCount) {
                        // do begin drop down
                        echo '<li class="dropdown">';
                        echo '<a href="#" class="dropdown-toggle" data-toggle="dropdown">';
                        echo '<i class="fa fa-caret-square-o-down"></i> More... <b class="caret"></b></a>';
                        echo '<ul class="dropdown-menu">';
                        echo '<li><a href="' . '../webpages/subCategory.php?cat_id=' . $subCat->cat_id . '"><i 
											class="fa fa-bar-chart-o"></i>' . $subCat->cat_name . '</a></li>';
                    } else if ($counter == $lastList + 1) {
                        // endropdow
                        echo ' </ul> </li>';
                    } else if ($counter <= $lastList + 1) {
                        echo '<li><a href="' . '../webpages/subCategory.php?cat_id=' . $subCat->cat_id . '"><i 
							class="fa fa-bar-chart-o"></i>' . $subCat->cat_name . '</a></li>';
                    }
                }
            }
            ?>


        </ul>


    </div><!-- /.navbar-collapse -->

</nav>