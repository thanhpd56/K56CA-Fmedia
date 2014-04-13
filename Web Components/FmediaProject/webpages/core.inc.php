<?php

ob_start();
session_start();

$current_file = $_SERVER['SCRIPT_NAME'];
$http_feferer = @$_SERVER['HTTP_REFERER'];

function loggedin() {
    if (isset($_SESSION['user_id']) and !empty($_SESSION['user_id'])) {
        return true;
    } else {
        echo $_SESSION['user_id'];
        return false;
    }
}

function getfield($field) {
    $query = "SELECT `$field` FROM `users_1` WHERE `id` = '" . $_SESSION['user_id'] . "'";
    if ($query_run = mysql_query($query)) {
        if ($mysql_result = mysql_result($query_run, 0, $field)) {
            return $mysql_result;
        }
    }
}

?>