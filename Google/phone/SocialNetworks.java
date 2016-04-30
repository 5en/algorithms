// Implement a social network. Need to define a data structure and two APIs:
//      friendsfriends()
//      top_friendsfriends()

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworks {

    private static class Person {
        Set<Person> friends;

        public Set<Person> friendsfriends() {
            Set<Person> ffs = new HashSet<Person>();
            for (Person friend : friends) {
                for (Person ff : friend.friends) {
                    if (ff != this && !friends.contains(ff)) {
                        ffs.add(ff);
                    }
                }
            }

            return ffs;
        }

        public List<Person> topFriendsfriends() {
            Map<Person, Integer> ffCount = new HashMap<Person, Integer>();
            for (Person friend : friends) {
                for (Person ff : friend.friends) {
                    if (ff == this || friends.contains(ff)) {
                        ffCount.put(ff, ffCount.containsKey(ff) ? ffCount.get(ff) + 1 : 1);
                    }
                }
            }

            List<Map.Entry<Person, Integer>> ffs = new ArrayList<Map.Entry<Person, Integer>>(
                ffCount.entrySet());
            Collections.sort(ffs, new Comparator<Map.Entry<Person, Integer>>() {
                @Override
                public int compare(Map.Entry<Person, Integer> e1, Map.Entry<Person, Integer> e2) {
                    return e1.getValue().compareTo(e2.getValue());
                }
            });

            List<Person> result = new ArrayList<Person>(ffs.size());
            for (Map.Entry<Person, Integer> entry : ffs) {
                result.add(entry.getKey());
            }

            return result;
        }
    }

}
