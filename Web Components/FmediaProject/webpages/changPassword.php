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
<?php ?>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Change password</title>
        <?php
        include '../View/style.php';
        $linkControl->changePassword($_SESSION['user_id']);
        ?>
    </head>
    <body> 
        
        <div class="container" id="nav_div">
        <?php
        $linkControl->showNavigationBar();
        ?>
        </div>
        
        <div class="container">
            <div class="row" >
                <?php
                $linkControl->showSideBar();
                ?>
                <div class="col-md-9" id="main_links">
                   <form id="defaultForm-pass" role="form" style="width:600px; padding-left:100px;" action="" method="POST" 
                       enctype="multipart/form-data">
                         
                       <div class="form-group">
                            <label for="exampleInputEmail1">Old Password</label>
                            <input type="password" class="form-control" id="title" name="old_pass" 
                                   placeholder="Enter title" value="">
                        </div>
                       <div class="form-group">
                            <label for="exampleInputEmail1">New Password</label>
                            <input type="password" class="form-control" id="title" name="new_pass" 
                                   placeholder="Enter title" value="">
                        </div>
                       <div class="form-group">
                            <label for="exampleInputEmail1">Retype New Password</label>
                            <input type="password" class="form-control" id="title" name="re_new_pass" 
                                   placeholder="Enter title" value="">
                        </div>
                       <div class="form-group" id="divsb">
                            <input class="sb" type="submit" class="form-control" id="validateBtn" value="Save" >
                        </div>	
                   </form>

                </div> 
            </div>
        </div>




    </body>
    <script type="text/javascript">
$(document).ready(function() {
    // Generate a simple captcha
    

    $('#defaultForm-pass').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            old_pass: {
                validators: {
                    notEmpty: {
                        message: 'The first name is required and cannot be empty'
                    }
                }
            },
            new_pass: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: 'The password must be more than 5 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: 'The password can only consist of alphabetical, number, dot and underscore'
                    },
                    identical: {
                        field: 're_new_pass',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            re_new_pass: {
                message: 'The password is not valid',
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: 'The password must be more than 5 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: 'The password can only consist of alphabetical, number, dot and underscore'
                    },
                    identical: {
                        field: 'new_pass',
                        message: 'The password and its confirm are not the same'
                    }
                    
                }
            }
            
       
        }
    });

    // Validate the form manually
    $('#validateBtn').click(function() {
        $('#defaultForm').bootstrapValidator('validate');
    });

    $('#resetBtn').click(function() {
        $('#defaultForm').data('bootstrapValidator').resetForm(true);
    });
});
</script>
</html>
<?php
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
?>