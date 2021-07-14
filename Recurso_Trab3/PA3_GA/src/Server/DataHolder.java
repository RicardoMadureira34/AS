/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author tsuru
 */
public class DataHolder {

    static class Data {

        String key;
        Integer value;

        public Data(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Data{"
                    + "key='" + key + '\''
                    + ", value=" + value
                    + '}';
        }
    }

}
