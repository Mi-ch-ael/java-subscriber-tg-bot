package ru.tinkoff.edu.java.linkParser.parsers;

public abstract class AbstractURLParser implements URLParser{
    protected URLParser next;
    public void setNext(URLParser next) {
        this.next = next;
    }
}
