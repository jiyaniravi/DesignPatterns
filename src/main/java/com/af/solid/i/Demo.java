
package com.af.solid.i;

class Document{

}

interface Machine{
    void print(Document d);
    void fax(Document d);
    void scan(Document d);

}

class MultiFunctionPrinter implements Machine
{
    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface Printer{
    void print(Document d);
}

interface Scanner{
    void scan(Document d);
}

class JustAPrinter implements Printer{
    @Override
    public void print(Document d) {

    }
}

// YAGNI : You Ain't Going to Need It

class OldFashionedPrinter{

}

public class Demo {
}

class PhotoCopier implements Printer, Scanner{
    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultiFunctionalDevice extends Printer, Scanner{}

class MultiFunctionalMachine implements MultiFunctionalDevice{

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionalMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}