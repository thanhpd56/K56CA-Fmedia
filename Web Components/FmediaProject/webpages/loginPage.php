<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <?php
        include '../View/style.php';
        require_once '../Controller/linkController.php';
        require 'connect.inc.php';
	require 'core.inc.php';
        //$linkControl = new linkController();
        $conn = mysqli_connect(HOST, USER, PASS, DB) or die('Could not connect to DB');
        if (isset($_POST['username']) && isset($_POST['password'])) {
            $username = mysql_real_escape_string($_POST['username']);
            $password = mysql_real_escape_string($_POST['password']);
            $password_hash = md5($password);
            if (!empty($username) and !empty($password)) {
                $query = "SELECT `id` FROM `users` WHERE `username` = '$username' AND `password` = '$password_hash'";
                if ($query_run = mysql_query($query)) {
                    $mysql_num_rows = mysql_num_rows($query_run);
                    if ($mysql_num_rows == 0) {
                        $error = 'Invalid username/password';
                    } else if ($mysql_num_rows == 1) {
                        $user_id = mysql_result($query_run, 0, 'id');
                        $_SESSION['user_id'] = $user_id;
                        header('Location: index.php');
                    }
                }else{
                    $error = "loi query";
                }
            } else {
                $error = 'Please subly username and password';
            }
        }
        ?>
    </head>
    <body>
        <header style="width: 500px; margin-top:80px; margin-left: 350px; background: #9acfea;">
            <form class="form-horizontal" role="form" style="padding: 30px; border: solid #0099FF 1px; border-radius: 5px;"
                  method="POST" action=""
                  >
                <div class="form-group">
                    <label  class="col-md-8 control-label" style="color: red;"><?php if (isset($error)) echo $error; ?></label>

                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="username" name="username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form>
        </header>
    </body>
</html>
