package com.example.demo.controller;

import com.example.demo.pojo.AESDataNeedToEncrypt;
import com.example.demo.pojo.DataNeedToDecrypt;
import com.example.demo.pojo.DataParam;
import com.example.demo.service.CryptoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class EncryptController {

    String path = "/home/hungbang/HungBang/UNGPAY/demo-controller/src/main/resources/application.properties";

    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    public ResponseEntity encryptData(@RequestBody DataParam dataParam) {
        String dataEncrypted = CryptoService.newInstance(path).encryptDataByAESKey(dataParam);
        return ResponseEntity.ok(dataEncrypted);
    }

    @RequestMapping(value = "/encryptKeyAES", method = RequestMethod.POST)
    public ResponseEntity encryptKeyAES(@RequestBody AESDataNeedToEncrypt dataParam) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String dataEncrypted = CryptoService.newInstance(path).encryptAESByRSAPublicKey(dataParam);
        return ResponseEntity.ok(dataEncrypted);
    }

    @RequestMapping(value = "/decrypt", method = RequestMethod.POST)
    public ResponseEntity decryptData(@RequestBody DataNeedToDecrypt dataParam) {
        String dataReturn = null;
        try {
            dataReturn = CryptoService.newInstance(path).decryptDataWithAESKeyEncrypted(dataParam);
        } catch (IOException e) {
            throw new RuntimeException("Error when decrypt data with AES key encrypted.");
        }
        return ResponseEntity.ok(dataReturn);
    }
}