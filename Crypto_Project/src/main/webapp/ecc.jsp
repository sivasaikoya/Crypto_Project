<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ECC Encryption and Decryption Tool">
    <title>HashEncrypt Pro</title>
    <link rel="stylesheet" href="css/ecc.css">
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
                    <form action="ecc">
                    <button class="generate_key">Generate ECC Key Pair</button>
                    <div class="key_gen_pub_pri">
                        <div class="inner_key_gen">
                            <div class="row_copy_gen">
                                <label class="key_head" for="public-key">Alice's Public Key</label>
                                <textarea class="key_box box1" id="public-key" readonly="" placeholder="Alice's public key will appear here">${apub}</textarea>
                            </div>
                            
                        </div>
                        <div class="inner_key_gen">
                            <div class="row_copy_gen">
                                <label class="key_head" for="private-key">Alice's Private Key</label>
                                <textarea class="key_box box1" id="private-key" readonly="" placeholder="Alice's private key will appear here">${apvt}</textarea>
                                
                            </div>
                            
                        </div>
                        <div class="inner_key_gen">
                          <div class="row_copy_gen">
                              <label class="key_head" for="public-key">Bobs's Public Key</label>
                              <textarea class="key_box box1" id="public-key" readonly="" placeholder="Bob's public key will appear here">${bpub}</textarea>
                              
                          </div>
                          
                      </div>
                      <div class="inner_key_gen">
                          <div class="row_copy_gen">
                              <label class="key_head" for="private-key">Bobs's Private Key</label>
                              <textarea class="key_box box1" id="private-key" readonly="" placeholder="Bob's private key will appear here">${bpvt}</textarea>
                              
                          </div>
                          
                      </div>
                    </div>
                    </form>
                </div>
            </div>
          
        </div>
        
        <!-- Bottom Section: Decryption and Result -->
        <div class="bottom-section">
            <!-- RSA Decryption Section -->
            <div class="rsa_enc_dec_in">
            
                <h2 class="rsa_en_de_head">Generate Shared Key</h2>
                <form action="sharedkey">
                <label class="select_size label2" for="manual-private-key">Enter Sender's Private Key</label>
                <textarea class="key_box size2" id="manual-private-key" rows="1" cols="50" placeholder="Enter Sender's Private Key..." name="pvt"></textarea>
                <label class="select_size label2" for="encrypted-text">Enter Reciever's Public Key</label>
                <textarea class="key_box size2" id="encrypted-text" rows="4" cols="50" placeholder="Enter Reciever's Public Key..." name="pub"></textarea>
                <button class="generate_key second">Generate</button>
                </form>
            </div>
            
            <!-- Result Section -->
            <div class="rsa_enc_dec_in">
                <h2 class="rsa_en_de_head">Shared Key</h2>
                <textarea class="result_output" id="result" readonly placeholder="The result will appear here">${sharedkey}</textarea>
                <button class="result_but">Copy Result</button>
            </div>
        </div>
    </div>
    

</body>
</html>