package com.neusoft.mall.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @ClassName: StringLengthEmptyUndefind
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */
@Configuration
public class StringLengthEmptyUndefind {
    public StringLengthEmptyUndefind(){

    }
    public boolean isEmpty(String[] str){
        for(int i=0;i<str.length;i++){
            if(str[i]==null || str[i]==""){
                return true;
            }
        }
        return false;
    }
    public boolean isLength(String[] str,int len){
        for(int i=0;i<str.length;i++){
            if(str[i].length()<len){
                return true;
            }
        }
        return false;
    }
    public boolean isUndefind(String[] str){
        for(int i=0;i<str.length;i++){
            if(str[i].equals("undefind")){
                return true;
            }
        }
        return false;
    }

    //true:空(为空或长度小于len，或undefind)， false：非空
    public boolean pd(String[] str,int len,boolean undefind){
        if(isEmpty(str)){
            return true;
        }
        if(undefind && isUndefind(str)){
           return true;
        }
        if( (len>0) && isLength(str,len) ){
            return true;
        }

        return false;
    }
}
