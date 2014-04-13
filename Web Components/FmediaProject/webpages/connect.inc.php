<?php
require_once './config.php';
    
if(!mysql_connect(HOST, USER, PASS) or !mysql_select_db(DB)){
	die(mysql_error());
}

?>