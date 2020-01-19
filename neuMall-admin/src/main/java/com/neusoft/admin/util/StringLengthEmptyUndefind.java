package com.neusoft.admin.util;

import org.springframework.stereotype.Service;

/**
 * @ClassName: StringLengthEmptyUndefind
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */
@Service
public class StringLengthEmptyUndefind {
    public StringLengthEmptyUndefind(){

    }
    public boolean pdEmpty(String[] str){
        for(int i=0;i<str.length;i++){
            if(str[i]==""||str[i]==null){
                return false;
            }
        }
        return true;
    }
    public boolean pdLength(String[] str,int len){
        for(int i=0;i<str.length;i++){
            if(str[i].length()<len){
                return false;
            }
        }
        return true;
    }
    public boolean pdUndefind(String[] str){
        for(int i=0;i<str.length;i++){
            if(str[i].equals("undefind")){
                return false;
            }
        }
        return true;
    }
    public boolean pd(String[] str,int len,boolean undefind){
        if(undefind){
            if(  pdEmpty(str) && pdUndefind(str) && pdLength(str,len)){
                return true;
            }
        }else {
            if(pdEmpty(str) && pdLength(str,len)){
                return true;
            }
        }
        return false;
    }
}
