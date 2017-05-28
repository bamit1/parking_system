package ib.parking.controller;

public class ErrorDto {
    
    private String msg;
    
    public ErrorDto(String msg) {
	this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
