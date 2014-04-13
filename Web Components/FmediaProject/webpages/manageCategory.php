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
        <title>Manage Category</title>
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
                    <form id="defaultForm" role="form" style="width:600px; padding-left:100px;" action="" method="POST" 
                          enctype="multipart/form-data">
                        
                            <div class="form-group" style="width:300px; position:relative;">
                                   
                                    <input style="float:left; margin:10px;" type="text" class="form-control"  name="add_new_cat" placeholder="Enter category name"
                                    >
                                 </div> 
                        <button style="float:left; margin:8px;" class="btn btn-info" type="button"  
                                         onclick="getType('addCategory.php?cat_name='+add_new_cat.value)"
                                         >Add New</button>
                              <div id="type_div">
                            <?php
                              foreach ($allCats as $cat) {
                                  echo ' 
                                  <div class="form-group" style="width:300px; position:relative;">
                                    <input style="float:left; margin:10px;" type="text" class="form-control" id="title" name="b'.$cat->id.'" 
                                    value="'.$cat->cat_name.'">
                                 </div>'; ?>
                                  <button style="float:left; margin:8px;" class="btn btn-danger" type="button"
                                 onclick="getType('deleteCategory.php?cat_id='+<?php echo $cat->id;?>)"
                                 >Delete</button>
                                  <button style="float:left; margin:8px;" class="btn btn-info" type="button"
                                         onclick="getType('updateCategory.php?cat_id='+<?php echo $cat->id;?>+'&cat_name='+ <?php echo'b'. $cat->id.'.value'?>)"
                                         >Update</button>
                            
                           <?php 
                            }
                              ?>  
                              </div>
                    </form>
                    
                        
                    
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