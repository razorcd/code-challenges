package com.other.challenges;


import java.util.concurrent.Callable;

interface Bird {
    Egg lay();
}

public class Chicken implements Bird {
    public Chicken() {
    }

    @Override
    public Egg lay() {
        return new Egg(new Callable<Bird>() {
            @Override
            public Bird call() throws Exception {
                return new Chicken();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Chicken chicken = new Chicken();
        System.out.println(chicken instanceof Bird);
        System.out.println(chicken instanceof Chicken);
        Egg egg = chicken.lay();
        System.out.println(egg);
        Bird newBird = egg.hatch();
        System.out.println(newBird);
    }
}

class Egg {
    private boolean hached;
    private Bird newBird;

    public Egg(Callable<Bird> createBird) {
        this.hached = false;
        try {
            this.newBird = createBird.call();
        } catch (Exception e) {e.printStackTrace();}
    }

    public Bird hatch() throws Exception {
        if (hached) throw new IllegalStateException();
        this.hached = true;
        return newBird;
    }
}