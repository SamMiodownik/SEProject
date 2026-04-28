package ds.projects.calendar;

import ds.projects.calendar.events.Event;
import java.util.*;

public class EventTrie{
	private static final int alphabetSize = 256;
    private Node root;
    
    public static class Node{
        protected Set<Day> days;
        protected Node[] links;
        private Node(){
            this.links = new Node[EventTrie.alphabetSize];
            this.days = new HashSet<>();
        }
    }

    public EventTrie(){
        this.root = new Node();
    }

    private Node getNode(String key){
        int d = 0;
        Node x = this.root;
        while(true){
            if(x == null){
                return null;
            }
            if(key.length() == d){
                return x;
            }
            char c = key.charAt(d);
            x = x.links[c];
            d++;
        }
    }

    public void put(String key, Day day){
        if (day == null){
            this.deleteAll(key);
        }
        else{
            this.root = put(this.root, key, day, 0);
        }
    }

    private Node put(Node x, String key, Day day, int d){
        if (x == null){
            x = new Node();
        }
        if (d == key.length()){
            if(x.days == null){
                x.days = new HashSet<>();
            }
            x.days.add(day);
            return x;
        }
        char c = key.charAt(d);
        x.links[c] = this.put(x.links[c], key, day, d + 1);
        return x;
    }

    public Set<Day> deleteAll(String key){
        Set<Day> set = new HashSet<>();
        this.root = deleteAll(this.root, key, 0, set);
        return set;
    }

    public Day delete(String key, Day day){
        Node del = this.getNode(key);
        if(del == null){
            return null;
        }
        if(del.days == null){
            return null;
        }
        for(Day setDay : (Set<Day>) del.days){
            if(setDay.equals(day)){
                del.days.remove(day);
                return day;
            }
        }
        return null;
    }

    private Node deleteAll(Node x, String key, int d, Set<Day> set){
        if (x == null){
            return null;
        }
        if (d == key.length()){
            if(x.days != null){
                set.addAll(x.days);
                x.days.clear();
            }
        }else{
            char c = key.charAt(d);
            x.links[c] = this.deleteAll(x.links[c], key, d + 1, set);
        }
        if (x.days != null){
            return x;
        }
        for (int c = 0; c <EventTrie.alphabetSize; c++){
            if (x.links[c] != null){
                return x;
            }
        }
        return null;
    }

    public Set<Day> deleteAllWithPrefix(String prefix){
        Set<Day> set = new HashSet<>();
        Node prefixNode = getNode(prefix);
        if(prefixNode != null){
            this.getAllChildValues(prefixNode, set);
            for(int c = 0; c < EventTrie.alphabetSize; c++){
                prefixNode.links[c] = null;
            }
        }
        return set;
    }

    private void getAllChildValues(Node x, Set<Day> set){
        if(x.days != null){
            set.addAll(x.days);
        }
        for(int c = 0; c < EventTrie.alphabetSize; c++){
            if(x.links[c] != null){
                this.getAllChildValues(x.links[c], set);
            }
        }
    }

    public Set<Day> get(String key){
        Set<Day> gets = new HashSet<>();
        this.get(this.root, key, 0, gets);
        return gets;
    }

    private void get(Node x, String key, int d, Set<Day> set){
        if (x != null){
            if (d == key.length()){
                if(x.days != null){
                    set.addAll(x.days);
                }
            }else{
                char c = key.charAt(d);
                this.get(x.links[c], key, d + 1, set);
            }
        }
    }
}