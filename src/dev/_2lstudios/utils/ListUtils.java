package dev._2lstudios.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;

public class ListUtils {
    public static List<Sound> toSoundList(final List<String> strings) {
        final List<Sound> sounds = new ArrayList<Sound>();

        if (strings != null && !strings.isEmpty()) {
            for (final Sound sound : Sound.values()) {
                if (strings.contains(sound.name())) {
                    sounds.add(sound);
                }
            }
        }

        return sounds;
    }

    private static int getRandomIndex(final List<?> list) {
        return (int) (Math.random() * list.size());
    }

    public static String getRandomString(final List<String> list) {
        if (list.isEmpty()) {
            return "";
        }

        return list.get(getRandomIndex(list));
    }

    public static Sound getRandomSound(final List<Sound> list) {
        if (list.isEmpty()) {
            return null;
        }

        return list.get(getRandomIndex(list));
    }
}
