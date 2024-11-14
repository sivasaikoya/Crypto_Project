<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="RSA Encryption and Decryption Tool">
    <title>HashEncrypt Pro</title>
    <link rel="stylesheet" href="css/rsa.css">
</head>
<body>
	
	<form action="rsa">
    	<h2 class="key_generate_head">Generate RSA Keys</h2>
        <label class="select_size" id="key_size" for="key-size">Select RSA Key Size</label>
        <select class="select_bar" id="key-size" name="bits">
       		<option value="512">512 bits</option>
        	<option value="1024">1024 bits</option>
        	<option value="2048">2048 bits</option>
        	<option value="3072">3072 bits</option>
       		<option value="4096">4096 bits</option>
        </select>
        <button class="generate_key">Generate RSA Key Pair</button>
   	</form>


</body>
</html>
