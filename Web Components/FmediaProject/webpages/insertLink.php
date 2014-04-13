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
        <title>Insert Video</title>
        <?php
        include '../View/style.php';
        require_once '../Controller/linkController.php';
        $linkControl = new linkController();
        $linkControl->insertVideo();
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

                        <div class="form-group">
                            <label for="exampleInputEmail1">Title</label>
                            <input type="text" class="form-control" id="title" name="title" 
                                   placeholder="Enter title">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Video Id</label>
                            <input type="text" class="form-control" id="url" name="url" placeholder="Enter url">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">During Time</label>
                            <input type="text" class="form-control" id="during_time" name="during_time">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputFile">Poster Url</label>
                            <input type="text" class="form-control" id="image_url" name="image_url" placeholder="Enter url">

                        </div>

                        <div class="form-group">
                            <label for="exampleInputEmail1">Description</label>
                            <textarea class="form-control" name="description" rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Rating</label>
                            <input type="text" class="form-control" id="rating" name="rating" >
                        </div>	

                        <div class="form-group">
                            <label for="exampleInputEmail1">Category</label>
                            <select class="form-control" name="category">
                                <option value="-1">Select Category</option>
                                <?php
                                $cates = $linkControl->model->listAllCategory();
                                foreach ($cates as $cat)
                                    echo '<option value="' . $cat->id . '">' . $cat->cat_name . '</option>';
                                ?>
                            </select>
                        </div>	
                        <div class="form-group">
                            <label for="exampleInputEmail1">Category</label>
                            <select class="form-control" name="category1">
                                <option value="-1">Select Category</option>
                                <?php
                                $cates = $linkControl->model->listAllCategory();
                                foreach ($cates as $cat)
                                    echo '<option value="' . $cat->id . '">' . $cat->cat_name . '</option>';
                                ?>
                            </select>
                        </div>	
                        <div class="form-group">
                            <label for="exampleInputEmail1">Category</label>
                            <select class="form-control" name="category2">
                                <option value="-1">Select Category</option>
                                <?php
                                $cates = $linkControl->model->listAllCategory();
                                foreach ($cates as $cat)
                                    echo '<option value="' . $cat->id . '">' . $cat->cat_name . '</option>';
                                ?>
                            </select>
                        </div>	
                        <div class="form-group">
                            <label for="exampleInputEmail1">Category</label>
                            <select class="form-control" name="category3">
                                <option value="-1">Select Category</option>
                                <?php
                                $cates = $linkControl->model->listAllCategory();
                                foreach ($cates as $cat)
                                    echo '<option value="' . $cat->id . '">' . $cat->cat_name . '</option>';
                                ?>
                            </select>
                        </div>	
                        <div class="form-group">
                            <label for="exampleInputEmail1">Category</label>
                            <select class="form-control" name="category4">
                                <option value="-1">Select Category</option>
                                <?php
                                $cates = $linkControl->model->listAllCategory();
                                foreach ($cates as $cat)
                                    echo '<option value="' . $cat->id . '">' . $cat->cat_name . '</option>';
                                ?>
                            </select>
                        </div>	
                        <div class="form-group" id="divsb">
                            <input class="sb" type="submit" class="form-control" id="validateBtn" value="Save" >
                        </div>	

                    </form>





                </div> 
            </div>
        </div>




    </body>
    <script src="../js/bootstrapValidator.js"></script>
    <script src="../js/bootstrapValidator.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function() {
            // Generate a simple captcha


            $('#defaultForm').bootstrapValidator({
                //        live: 'disabled',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    title: {
                        validators: {
                            notEmpty: {
                                message: 'The title is required and cannot be empty'
                            },
                            stringLength: {
                                min: 5,
                                max: 300,
                                message: 'The title must be more than 5 characters long'
                            }
                        }
                    },
                    url: {
                        message: 'The url is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The url is required and cannot be empty'
                            },
                            stringLength: {
                                min: 5,
                                message: 'The url must be more than 5 characters long'
                            }
                        }
                    },
                    image_url: {
                        message: 'The image url is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The url is required and cannot be empty'
                            },
                            stringLength: {
                                min: 5,
                                message: 'The url must be more than 5 characters long'
                            },
                            uri: {
                                message: 'the url is invalid'
                            }


                        }
                    },
                    description: {
                        message: 'The description is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The field is required and cannot be empty'
                            },
                            stringLength: {
                                min: 5,
                                message: 'The field must be more than 5 characters long'
                            }

                           

                        }
                    },
                    during_time: {
                        message: 'The field is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The field is required and cannot be empty'
                            }
                     
                        }
                    },
                    rating: {
                        message: 'The field is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The field is required and cannot be empty'
                            },
                            regexp: {
                                regexp: /^[0-9\.]+$/,
                                message: 'The fied must be number type'
                            }
                        }
                    },
                   
                }
            });
            // Validate the form manually
            $('#validateBtn').click(function() {
                $('#defaultForm').bootstrapValidator('validate');
            });
        });</script>
</html>
<?php
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
?>