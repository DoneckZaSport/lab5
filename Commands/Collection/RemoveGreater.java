package Commands.Collection;

import Commands.Command;
import Ticket.Ticket;
import Ticket.TicketCollection;
import Utility.Invoker;

import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

public class RemoveGreater implements Command {
    public RemoveGreater(){
        Invoker.regist("remove_greater",this);
    }
    @Override
    public void execute(String par1) throws IOException {
        if(par1==null&&ExecuteScript.inExecution){
            System.out.println("Параметр не был указан,выполнение команды \"remove_greater\" невозможно.");
            System.out.print("$ ");
        } else
        if (par1 == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите ключ");
            System.out.print("$ ");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {
                System.out.println("Ключ не может быть null");
                this.execute(par1);
            } else this.execute(key);
        } else {
            try {
                TicketCollection ticketCollection = new TicketCollection();
                if (ticketCollection.getSize() == 0) {
                    System.out.println("Коллекция как бы пустая.");
                    System.out.print("$");
                } else {
                    Iterator it = ticketCollection.getTickets().entrySet().iterator();
                    long givenId = Long.parseLong(par1);
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        Ticket ticket = (Ticket) pair.getValue();
                        long currentId = ticket.getMapKey();
                        if (currentId > givenId) {
                            it.remove();
                        }

                    }
                    System.out.println("Все возможные обьекты были удалены.");
                    System.out.print("$ ");
                }
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Ключ указан некорректно,попробуйте ещё раз.");
            }
        }
    }

    @Override
    public String getInfo() {
        return "---> Удалить все элементы,чей ключ больше данного" ;
    }
}
