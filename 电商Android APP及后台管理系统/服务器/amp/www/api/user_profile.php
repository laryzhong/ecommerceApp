<?php
$file_name="http://127.0.0.1/data/user_profile.json";
$file=file_get_contents($file_name);
echo $file;