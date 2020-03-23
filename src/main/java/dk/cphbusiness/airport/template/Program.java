package dk.cphbusiness.airport.template;

import dk.cphbusiness.algorithm.examples.queues.NotPrioritisingPassengerArrayQueue;
import dk.cphbusiness.algorithm.examples.queues.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Program {
  private static List<Plane> planes = new ArrayList<>();
  private static PassengerProducer producer;
  private static PassengerConsumer consumer;
  private static PriorityQueue<Passenger> queue;
  private static Clock clock;
  
  private static void setup() {
    for (int hour = 7; hour <= 22; hour++) {
      planes.add(new Plane(new Time(hour, 00, 00)));
      }
    queue = new NotPrioritisingPassengerArrayQueue(10000);
    producer = new PassengerProducer(planes, queue);
    consumer = new PassengerConsumer(planes, queue);
    clock = new Clock(producer, consumer, new Time(05, 00, 00));
    }
  
  public static void main(String[] args) {
    setup();
    System.out.println("Hello Airport");
    new Thread(clock).start();
    /*
    Passenger[] passengers = { new Passenger(1, new Time(100), Category.Disabled, new Plane(new Time(123))),
            new Passenger(2, new Time(100), Category.BusinessClass, new Plane(new Time(123))),
            new Passenger(3, new Time(100), Category.Disabled, new Plane(new Time(123))),
            null, null};

    printArray(NotPrioritisingPassengerArrayQueue.mergeSort(passengers));

    */
    }


    public static void printArray(Passenger[] array) {
      for (int i = 0; i < array.length ; i++) {
        if(array[i] == null) {
          System.out.println("null");
        } else {
          System.out.println(array[i].getId());
        }
      }
    }
  
  }
