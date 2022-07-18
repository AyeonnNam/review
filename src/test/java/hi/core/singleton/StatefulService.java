package hi.core.singleton;

public class StatefulService {


      //  private int price;

      int order(String name, int price){
            System.out.println("name = " + name + ", price = " + price);
            return price;

            //this.price = price;
        }

//        public int getPrice(){
//                return price;
//    }



}
