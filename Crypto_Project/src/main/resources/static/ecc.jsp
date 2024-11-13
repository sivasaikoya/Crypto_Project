<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ECC Encryption and Decryption Tool">
    <title>HashEncrypt Pro</title>
    <link rel="stylesheet" href="css/rsa.css">
</head>
<body>

    <div id="root">
        <!-- Main Heading -->
        <h1 class="main-heading">ECC Encryption and Decryption</h1>
        
        <!-- Top Sections: Left (Generate ECC Keys) and Right (RSA Encryption) -->
        <div class="top-section">
            <!-- Generate ECC Keys Section (Left) -->
            <div class="key_gen_section">
                
                <div class="key_gen_back">
                    <h2 class="key_generate_head">Generate ECC Keys</h2>
                    <label class="select_size" id="key_size" for="key-size">Select ECC Key Size</label>
                    <select class="select_bar" id="key-size">
                        <option value="512">512 bits</option>
                        <option value="1024">1024 bits</option>
                        <option value="2048">2048 bits</option>
                        <option value="3072">3072 bits</option>
                        <option value="4096">4096 bits</option>
                    </select>
                    <button class="generate_key">Generate ECC Key Pair</button>
                    <div class="key_gen_pub_pri">
                        <div class="inner_key_gen">
                            <div class="row_copy_gen">
                                <label class="key_head" for="public-key">Alice's Public Key</label>
                                <textarea class="key_box box1" id="public-key" readonly="" placeholder="Your public key will appear here"></textarea>
                                <button class="copy">Copy</button>
                            </div>
                            
                        </div>
                        <div class="inner_key_gen">
                            <div class="row_copy_gen">
                                <label class="key_head" for="private-key">Alice's Private Key</label>
                                <textarea class="key_box box1" id="private-key" readonly="" placeholder="Your private key will appear here"></textarea>
                                <button class="copy">Copy</button>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- RSA Encryption Section (Right) -->
            <div class="encrypt_decrypt_section">
                <h2 class="rsa_en_de_head">RSA Encryption</h2>
                <label class="select_size label2" for="manual-public-key">Enter Public Key</label>
                <textarea class="key_box size2" id="manual-public-key" rows="1" cols="50" placeholder="Enter Public Key"></textarea>
                <label class="select_size label2" for="plain-text">Plain Text</label>
                <textarea class="key_box size2" id="plain-text" rows="4" cols="50" placeholder="Enter Plain Text..."></textarea>
                <button class="generate_key second">Encrypt</button>
            </div>
        </div>
        
        <!-- Bottom Section: Decryption and Result -->
        <div class="bottom-section">
            <!-- RSA Decryption Section -->
            <div class="rsa_enc_dec_in">
                <h2 class="rsa_en_de_head">Generate Shared Key</h2>
                <label class="select_size label2" for="manual-private-key">Enter Sender's Private Key</label>
                <textarea class="key_box size2" id="manual-private-key" rows="1" cols="50" placeholder="Enter Private Key"></textarea>
                <label class="select_size label2" for="encrypted-text">Enter Reciever's Public Key</label>
                <textarea class="key_box size2" id="encrypted-text" rows="4" cols="50" placeholder="Enter Encrypted Text..."></textarea>
                <button class="generate_key second">Generate</button>
            </div>
            
            <!-- Result Section -->
            <div class="rsa_enc_dec_in">
                <h2 class="rsa_en_de_head">Shared Key</h2>
                <textarea class="result_output" id="result" readonly placeholder="The result will appear here"></textarea>
                <button class="result_but">Copy Result</button>
            </div>
        </div>
    </div>
    

</body>
</html>
