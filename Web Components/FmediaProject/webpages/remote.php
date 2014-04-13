<?php

// This is a sample PHP script which demonstrates the 'remote' validator
// To make it work, point the web server to root Bootstrap Validate directory
// and open the remote.html file:
// http://domain.com/demo/remote.html
//sleep(5);

$valid = true;

echo json_encode(array(
    'valid' => $valid,
));
