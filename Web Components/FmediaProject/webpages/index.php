<?php
    require './connect.inc.php';
    require './core.inc.php';
    if(!loggedin())
    {
        header('location: loginPage.php');
    }
    require_once '../Controller/linkController.php';
    $linkControl = new linkController();
    
?>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Manage video</title>
        <?php
        include '../View/style.php';
        
        $allCats = $linkControl->model->listAllCategory();
        ?>
    </head>
    <body> 

        <div class="container" id="nav_div">
            <?php
            $linkControl->showNavigationBar();
            ?>
        </div>

        <div class="container">
            <div class="row">
                <?php
                $linkControl->showSideBar();
                ?>
                <div class="col-md-9" id="main_links">
                    <?php
                    $linkControl->showAllLinks();
                    ?>
                    
                </div> 
            </div>
        </div>




    </body>
</html>
<?php
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
?>