package mcc;

import java.util.ArrayList;

/*

README
------

Below there's a class called Challenge with only one method: main.

Your mission, should you choose to accept it, is to add codes ONLY in the sections labeled "PLACE YOUR CODE HERE"
in order to:

1. Make this file compiles without any errors, and

2. Make the resulting application prints as many "true"s as possible. That is, the expected result is something like
   this:

    1.1 : true
    1.2 : true
    2.1 : true
    2.2 : true
    3.1 : true
    3.2 : true


Submission Instructions:
------------------------

1. Create a Java project with your name as the name of the project.
2. Complete your mission and ensure and all have been following the above guidelines.
3. Compress the entire project into a file OR push the project into a GIT repository
4. Submit the link to your answer (uploaded file or repository) to http://bit.ly/mastercodesubmission

 */
public class Challenge {

    public static void main(String[] args) {
        //
        // 1. Change car engine
        //
        Car car = new Car();
        Engine v6Engine = new V6Engine();

        // PROBLEM
        double v6EngineSpeed = car.getMaxSpeed();
        System.out.println("1.1 : " + (v6EngineSpeed == v6Engine.getMaxSpeed()));

        Engine turbopropEngine = new TurbopropEngine();
        car.changeEngine(turbopropEngine);
        double turbopropEngineSpeed = car.getMaxSpeed();

        // PROBLEM
        System.out.println("1.2 : " + (turbopropEngineSpeed > v6EngineSpeed));

        //
        // 2. Custom HTML-like markup language
        //
        MarkupElement root = new RootElement("[customml]", "[/customml]");
        MarkupElement body = new BodyElement("[body]", "[/body]");
        MarkupElement italic = new ItalicElement("[i]", "[/i]");

        italic.setContent("I am italic.");

        // PROBLEM
        System.out.println("2.1 : " + italic.produceOutput()
                .equals("[i]I am italic.[/i]"));
        root.addChildren(body);
        body.addChildren(italic);

        System.out.println("2.2 : " + root.produceOutput()
                .equals("[customml][body][i]I am italic.[/i][/body][/customml]"));

        //
        // 3. Implement stack that will output n items at once
        //
        WeirdStack<Integer> weirdStack = new WeirdStack<Integer>(2); // will output 2 items at once.
        weirdStack.push(1);
        weirdStack.push(2);
        weirdStack.push(3);

        java.util.List<Integer> popped = weirdStack.pop();
        // PROBLEM: Verify that popped is [3, 2]
        System.out.println("3.1 : " + popped.equals(java.util.Arrays.asList(3, 2)));

        weirdStack.setPopSize(3); // will output 3 items at once.
        weirdStack.push(4);
        weirdStack.push(5);

        java.util.List<Integer> poppedAgain = weirdStack.pop();
        // PROBLEM: Verify that poppedAgain is [5, 4, 1]
        System.out.println("3.2 : " + poppedAgain.equals(java.util.Arrays.asList(5, 4, 1)));
    }
}

//
// PROBLEM 1. Change car engine.
//
class Car {

    // PLACE YOUR CODE HERE
    private Engine engine;

    public Car() {
        engine = new V6Engine();
    }

    public double getMaxSpeed() {
        return engine.getMaxSpeed();
    }

    public void changeEngine(Engine eng) {
        engine = eng;
    }
}

interface Engine {

    // PLACE YOUR CODE HERE
    public double getMaxSpeed();
}

class V6Engine implements Engine {

    // PLACE YOUR CODE HERE
    private double maxSpeed;

    public V6Engine() {
        setMaxSpeed(1000);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

}

class TurbopropEngine implements Engine {

    // PLACE YOUR CODE HERE
    private double maxSpeed;

    public TurbopropEngine() {
        setMaxSpeed(1500);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}

//
// PROBLEM 2. Custom HTML-like markup language.
//
class MarkupElement {

    // PLACE YOUR CODE HERE
    private String content,
            openTag, closeTag, open, middle, close;
    private MarkupElement children;

    public MarkupElement(String openTag, String closeTag) {
        this.openTag = openTag;
        this.closeTag = closeTag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String produceOutput() {
        open = "";
        middle = "";
        close = "";
        recursive(this);
        return open + middle + close;
    }

    private void recursive(MarkupElement parent) {
        MarkupElement el = null;
        if (parent.getChildren() != el) {
            this.open += parent.openTag;
            this.close = parent.closeTag + this.close;
            recursive(parent.getChildren());
        } else {
            this.middle = parent.openTag + parent.content + parent.closeTag;
        }
    }

    public void addChildren(MarkupElement children) {
        this.children = children;
    }

    private MarkupElement getChildren() {
        return this.children;
    }
}

class RootElement extends MarkupElement {

    // PLACE YOUR CODE HERE
    public RootElement(String openTag, String closeTag) {
        super(openTag, closeTag);
    }

}

class BodyElement extends MarkupElement {

    // PLACE YOUR CODE HERE
    public BodyElement(String openTag, String closeTag) {
        super(openTag, closeTag);
    }
}

class ItalicElement extends MarkupElement {

    // PLACE YOUR CODE HERE
    public ItalicElement(String openTag, String closeTag) {
        super(openTag, closeTag);
    }

}

//
// PROBLEM 3. Implement stack that will output n items at once.
//
class WeirdStack<T> {
    
    // PLACE YOUR CODE HERE
    private ArrayList<Integer> list;
    private int popSize;

    public WeirdStack(int popSize) {
        list = new ArrayList<Integer>();
        setPopSize(popSize);
    }

    public void push(int i) {
        list.add(i);
    }

    public ArrayList<Integer> pop() {
        ArrayList<Integer> pop = new ArrayList<Integer>();
        for (int i = list.size() - 1; i > (list.size() - 1) - 2; i--) {
            pop.add(list.get(i));
        }
        popSize -= 2;
        if (popSize > 0) {
            for (Integer integer : list) {
                pop.add(integer);
                popSize--;
                if (popSize == 0) {
                    break;
                }
            }
        }
        return pop;
    }

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

}
