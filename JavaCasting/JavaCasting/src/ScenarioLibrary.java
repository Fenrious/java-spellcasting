import javafx.scene.image.Image;

import java.util.*;
import java.util.function.Consumer;

/**
 * This program contains all of the Scenarios that can be seen by the player. It will be used to access them easily
 * without displaying them in the main program, JavaCast.
 *
 * Last Modified: 10 / 17 / 19
 *
 * @author Shirley Krogel
 */

public class ScenarioLibrary {

    Timer timer = new Timer();
    HashMap<String, Scenario> library = new HashMap<>();
    Image classmate = new Image("Classmate.png");
    Image Schaffung = new Image("MrSchaffung.png");
    Image Ruben = new Image("Ruben.png");
    Image Treble = new Image("Treble.png");

    Image slime = new Image("Slime.png");
    Image elemental = new Image("Elemental.png");

    public ScenarioLibrary(JavaCast jc) {
        jc.getEnvironmentObjects().put("Dorms", new HashMap<>());
        jc.getEnvironmentObjects().put("Auditorium", new HashMap<>());
        jc.getEnvironmentObjects().put("Bakery", new HashMap<>());
        jc.getEnvironmentObjects().put("Village", new HashMap<>());
        jc.getEnvironmentObjects().put("User", new HashMap<>());
        library.put("Start", new Scenario(jc, classmate, "Hey, are you awake yet?", "FirstButton",
                null, (jc1) -> {
            jc.getUser().setQualityTrue("wait");
        }));
        library.put("FirstButton", new Scenario(jc, classmate, "Hold on, let me make a button " +
                "for you to click.", "Click?", null, (jc1) -> {
            jc.getEnvironmentObjects().get("Dorms").put("awake", new ButtonItem("\"awake\"", jc));
            Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).getTimer().schedule(library.get("NoInfo?"), 0);
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Dorms").remove("awake");
            };
            jc.getEnvironmentObjects().get("Dorms").get("awake").setVariable("OnAction", newOnAction);
            jc.getTarget().setVisible(true);
            jc.setWorldBackground("Dorms");
            jc.getUser().setQualityTrue("click");
            jc.getUser().setQualityTrue("Log");
        }));
        library.put("Click?", new Scenario(jc, classmate, "Wait, do you know how to click them? Check out" +
                " your Codex. The command is codex.show(); in your casting console. The 'Environment' tab should " +
                "show you what the button is called, and the 'Button' tab should show you how to click them.",
                null, null, null));
        library.put("NoInfo?", new Scenario(jc, classmate, "If you’re wondering why your Codex doesn’t" +
                " have any information on how to make a Button, it’s because you haven’t been taught how to " +
                "yet. I… might have snuck out to learn about them ahead of time.", "YouDoIt",
                null, null));
        library.put("YouDoIt", new Scenario(jc, classmate, "I can’t really teach you properly yet, " +
                "so you’re going to have to find someone that’s more confident with Buttons.", "HeySo",
                null, null));
        library.put("HeySo", new Scenario(jc, classmate, "I can tell you that the animals in this " +
                "world don’t seem to react to any of the custom buttons I make.", "Announcement",
                null, null));
        library.put("Announcement", new Scenario(jc, classmate, "*Greetings all Freshman, please make your " +
                "way to the school’s Auditorium*", "Reaction", null, (jc1) -> {
                    jc.getEnvironmentObjects().get("Dorms").put("auditorium", new ButtonItem("\"auditorium\"", jc));
                    Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                        ((JavaCast) var.get("JavaCast")).getUser().setLocation("Auditorium");
                        ((JavaCast) var.get("JavaCast")).setWorldBackground("Auditorium");
                        ((JavaCast) var.get("JavaCast")).getTimer().schedule(library.get("Reunion"), 0);
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Dorms").remove("auditorium");
                    };
                    jc.getEnvironmentObjects().get("Dorms").get("auditorium").setVariable("OnAction", newOnAction);
                }));
        library.put("Reaction", new Scenario(jc, classmate, "Looks like it’s time. Here, I’ll make you a " +
                "button so you can go whenever you want. Just click on it like any other button. See you there.",
                null, null, (jc1) -> {
                    jc1.getTarget().setVisible(false);
                    jc1.getUser().setQualityTrue("Dorms");
                    }));
        library.put("Reunion", new Scenario(jc, classmate, "Hello again. I see the button worked out. " +
                "I got a bit worried after I left.", "Shush", null, (jc1) -> {
                    jc.getTarget().setVisible(true);
                    jc.getUser().setQualityTrue("AuditoriumKnown");
                }));
        library.put("Shush", new Scenario(jc, classmate, "Oh, hey. I think it’s starting.",
                "Welcome", null, null));
        library.put("Welcome", new Scenario(jc, Schaffung, "Welcome, everyone. I trust that you had a " +
                "comfortable night’s rest in the dorms.", "Today", null, null));
        library.put("Today", new Scenario(jc, Schaffung, "Today is a very special day. Those of you" +
                " who have just enrolled in our prestigious Institute will be going on a journey to " +
                "learn what you can about your powers from the residents of this land.",
                "Goal", null, null));
        library.put("Goal", new Scenario(jc, Schaffung, "Your main goal will be to reach the Mountain " +
                "at the End of the World. While you could just journey straight there, you are highly encouraged" +
                " to learn everything you can. Afterall, your powers will be limited by what you know, and if you" +
                " do not have much to draw from, you might start to feel like you are not cut out to be an Avatar.",
                "Encouragement", null, null));
        library.put("Encouragement", new Scenario(jc, Schaffung, "It is not wrong or weird to feel this " +
                "way, but make sure that you are not giving up on something you truly want to dedicate your " +
                "life to.",
                "TakeYourTime", null, null));
        library.put("TakeYourTime", new Scenario(jc, Schaffung, "Those of you that return early will not" +
                " have much to do until the last student returns, so feel free to take your time on this journey." +
                " I’m sure some of you have already been able to take a look at your Codex. This is the most " +
                "invaluable resource for an Avatar because it will be difficult to remember all the uses of " +
                "everything you’ve learned.", "FamiliarStart", null, (jc1) -> {
                    jc.getUser().setQualityTrue("Codex");
                }));
        library.put("FamiliarStart", new Scenario(jc, Schaffung, "Speaking of resources that will be " +
                "available to you, we, the Avatar Progression Institute, have prepared special ‘familiars’ " +
                "that can help you when you are feeling lost on what to do. Our head researchers have been " +
                "working on making sure that these creatures will be able to provide some guidance in any " +
                "situation you may encounter on your journey.", "FamiliarSecond", null,
                null));
        library.put("FamiliarSecond", new Scenario(jc, Schaffung, "All you will need to do is click" +
                " on the Button associated with the familiar you wish to have. A familiar of the selected type " +
                "will appear beside you. This familiar is created from your own magic and is loyal to you. It will" +
                " not leave your side while you are within the Valley of Luminous Emeralds. Simply issue the " +
                "command familiar.help(); and it shall reply with whatever guidance it can give.",
                "FamiliarReceive", null, (jc1) -> {
                    jc.getUser().setQualityTrue("Familiar");
                }));
        library.put("FamiliarReceive", new Scenario(jc, Schaffung, " In order to click a Button, you will" +
                " need to find its name in your Codex under the ‘Environment’ tab. Once you have found the Button " +
                "you wish to click, issue a command starting with the Button’s name, then add .click(); to the end" +
                " of the command. The information should now be available in your Codex.", null,
                null, (jc1) -> {
                    jc.getEnvironmentObjects().get("Auditorium").put("slime", new ButtonItem("\"slime\"", jc));
                    Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Auditorium").remove("slime");
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Auditorium").remove("elemental");
                        ((JavaCast) var.get("JavaCast")).getUser().setFamiliar("Slime");
                        ((JavaCast) var.get("JavaCast")).setFamiliarAppearance(slime);
                        ((JavaCast) var.get("JavaCast")).familiarSays("Hey there, boss. I'll be by your side for this whole journey. " +
                                "I'll try to help you out as best as I can.");
                        ((JavaCast) var.get("JavaCast")).getLibrary().put("Busy", new Scenario(jc, Ruben, "There is a stampede of eager" +
                                " freshmen storming out of the University. You and your slime end up getting " +
                                "tossed around in all the chaos. You even lose track of your classmate.",
                                "Safety", null, (jc2) -> {
                            jc.getTarget().setVisible(false);
                            jc.setWorldBackground("Blackout");
                            jc.familiarSays("Boss? Are you ok!? There are so many people here.");
                        }));
                        ((JavaCast) var.get("JavaCast")).getTimer().schedule(library.get("Map"), 0);
                    };
                    jc.getEnvironmentObjects().get("Auditorium").get("slime").setVariable("OnAction", newOnAction);
                    jc.getEnvironmentObjects().get("Auditorium").put("elemental", new ButtonItem("\"elemental\"", jc));
                    newOnAction = (var) -> {
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Auditorium").remove("slime");
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Auditorium").remove("elemental");
                        ((JavaCast) var.get("JavaCast")).getUser().setFamiliar("Slime");
                        ((JavaCast) var.get("JavaCast")).setFamiliarAppearance(slime);
                        ((JavaCast) var.get("JavaCast")).familiarSays("Hey there, boss. I'll be by your side for this whole journey. " +
                        "I'll try to help you out as best as I can.");
                        ((JavaCast) var.get("JavaCast")).getLibrary().put("Busy", new Scenario(jc, Ruben, "There is a stampede of eager" +
                        " freshmen storming out of the University. You and your elemental end up getting " +
                        "tossed around in all the chaos. You even lose track of your classmate.",
                        "Safety", null, (jc2) -> {
                            jc.getTarget().setVisible(false);
                            jc.setWorldBackground("Blackout");
                            jc.familiarSays("Boss? Are you ok!? There are so many people here.");
                    }));
                        ((JavaCast) var.get("JavaCast")).getTimer().schedule(library.get("Map"), 0);
                };
                jc.getEnvironmentObjects().get("Auditorium").get("elemental").setVariable("OnAction", newOnAction);

        }));
        library.put("Map", new Scenario(jc, Schaffung, "Now that all of you have your companion, I would " +
                "like to tell you about a special feature that will help you navigate through the world during your" +
                " journey. If you issue the command map.show(); at any time, you will be able to see a map of the " +
                "area.", "MapInfo", null, null));
        library.put("MapInfo", new Scenario(jc, Schaffung, "If the map is visible and you issue the " +
                "command map.goTo(“Auditorium”); then you will travel back here. Your map will show you the name " +
                "of each place you know about. You can only travel to places that you know about, and you can only" +
                " travel once you have learned all you can from your current area. When you no longer wish to look" +
                " at the map, simply issue the command map.hide(); to put it away.", "Wishes",
                null, (jc1) -> {
                    jc.getUser().setQualityTrue("Map");
                }));
        library.put("Wishes", new Scenario(jc, Schaffung, "With a companion and a map, you are all ready " +
                "to start your journey. You may simply open up your map and issue the command map.goTo(“Market”); to" +
                " start the first leg of your journey. I wish you all the best of luck and that you will have a safe" +
                " and fruitful journey.", null, null, (jc1) -> {
                    jc.getUser().setQualityTrue("Auditorium");
                    jc.getUser().setQualityTrue("MarketKnown");
                    jc.setHintMessage("Mr. Schaffung said what command you need to use. Remember to have your" +
                            " map open so you can see what location you are trying to reach.");
                }));
        library.put("Safety", new Scenario(jc, Ruben, "You eventually end up being pushed into one of the" +
                " shops. Thankfully, it isn’t crowded. In fact, it seems like no one is here. At least you’re safe" +
                " from all the excitement going on outside.", "SoQuick", null, (jc1) -> {
                    jc.setHintMessage(null);
                    jc.setWorldBackground("Bakery");
                    jc.getUser().setQualityTrue("BakeryKnown");
                 }));
        library.put("SoQuick", new Scenario(jc, Ruben, "Is it that time of year already? I was so busy " +
                        "making these cookies that I hadn’t thought about what day it was.", "Ruben",
                        null, (jc1) -> {
                            jc.getTarget().setVisible(true);
                        }));
        library.put("Ruben", new Scenario(jc, Ruben, "My name is Ruben. I am the owner and sole employee" +
                " of Ruben’s Bakery. I’ll be teaching you a few basic things to prepare you for the next part of " +
                "your journey.", "Button?", null, (jc1) -> {
                    jc.getTarget().setVisible(true);
                }));
        library.put("Classes", new Scenario(jc, Ruben, "The first thing that I need to tell you about" +
                " is classes. Classes can be thought of as the definition of a narrow category of objects. An" +
                " example could be to think of all the people you know. All of them are people, and, thus, they" +
                " would belong to a class called Person. Each person could be considered an independent instance" +
                " of the Person class because their traits belong to them rather than the overall class called " +
                "Person. Classes hold information on their variables and their methods which could be used by other" +
                " classes or even you.", "Button?", null, (jc1) -> {
                    jc.getUser().setQualityTrue("ClassesInfo");
                }));
        library.put("Button?", new Scenario(jc, Ruben, "Now we’ll get into what a method is. You " +
                "already know how to click a button, but you haven’t had too much experience with using different" +
                " kinds of objects, right? All objects require the same three things to use them. Let me think of" +
                " an example…", "Methods", null, null));
        library.put("Methods", new Scenario(jc, Ruben, "Let’s see… ah, something you’ve had to click would" +
                " be something like “awake.click();”, right? That is a method call. It calls a method.",
                "MethodsSecond", null, null));
        library.put("MethodsSecond", new Scenario(jc, Ruben, "Methods are a series of predetermined actions" +
                " that can be called using an object, or class, that it is a method of. In this case, “awake” is " +
                "a Button, and “click” is one of the methods of the Button class. Let’s break that call down into" +
                " the three chunks. ", "FirstChunk", null, null));
        library.put("FirstChunk", new Scenario(jc, Ruben, "The first chunk is “awake”. It is the name, or" +
                " identity, of the object you are trying to use. Normally, it can be replaced by the result of " +
                "another method, but, for this journey, API locked that functionality of your command console to" +
                " keep things relatively simple. I’ll be talking about the results of methods later on, so I’ll " +
                "revisit this at that time.", "SecondChunk", null, null));
        library.put("SecondChunk", new Scenario(jc, Ruben, "The second chunk is “.click”. It is the name of" +
                " the method, or series of actions, you wish to perform on the given object. On your journey, it " +
                "will be rare for you to be able to call a method without referencing an object before it, unless" +
                " you are using the wait(); method, so remember to include the “.” between the object and its" +
                " method.", "ThirdChunk", null, null));
        library.put("ThirdChunk", new Scenario(jc, Ruben, "The third chunk is the “()” after click. This" +
                " is the section of calling a method where you can pass values into the method. Not all methods" +
                " require anything to be passed. awake.click() is an example of this. An example of a method " +
                "that requires something to be passed in would be “bread.bake(450);”. The 450 in the parenthesis" +
                " is passed into the awaiting variable within the method. This allows the method to take different" +
                " actions or return different values based on what it is passed. Regardless of whether or not the" +
                " parenthesis contain anything, they must be included after the name of the method.",
                "ExtraChunk", null, null));
        library.put("ExtraChunk", new Scenario(jc, Ruben, "This part isn’t particular to calling a method," +
                " but a “;” must be placed after every single-line command, otherwise, it will not be recognized as" +
                " a command. In a later area, you can learn about commands that span multiple lines.",
                "Don'tWorry", null, null));
        library.put("Don'tWorry", new Scenario(jc, Ruben, "If what I’ve been saying has been a bit of an" +
                " information overload, don’t worry. You can now access all of the information I spoke about by " +
                "using your Codex. The information is under the “Classes” and “Methods” tabs. Remember to check " +
                "your Codex whenever you are feeling lost.", "ConstructorStart", null, (jc1) -> {
                    jc.getUser().setQualityTrue("Method");
                    jc.familiarSays("And don't forget that you can ask me for help, boss. Just issue " +
                            "familiar.hint(); and I'll try to help you out.");
                }));
        library.put("ConstructorStart", new Scenario(jc, Ruben, "Speaking of methods, we’ve come to our " +
                "next topic. There is a special method called the “Constructor” method. It can’t be called by normal" +
                " means. It is only called once, and that is when an instance of a class is created. As the name " +
                "suggests, it “constructs” the object based on the list of instructions it has.",
                "ConstructorSecond", null, null));
        library.put("ConstructorSecond", new Scenario(jc, Ruben, "Like with other method calls, the " +
                "constructor has a set of parenthesis through which it can accept variables. An example command " +
                "to call a constructor would be Donut vanil = new Donut(“Vanilla”);\n" +
                "Let’s break this down into parts as well.\n", "ConstructorThird", null,
                null));
        library.put("ConstructorThird", new Scenario(jc, Ruben, "The first part of the command, “Donut”, " +
                "signals what class we want our variable to belong to. This means that our variable is a Donut.",
                "ConstructorFourth", null, null));
        library.put("ConstructorFourth", new Scenario(jc, Ruben, "The second part, “vanil”, is the name of " +
                "our variable. If we want to reference it later, then we will need to use this name. While there are " +
                "a lot of options for names of variables, it is heavily advised that you make all variable names " +
                "descriptive of what they are. If we called our variable “fred”, it would be very difficult for us" +
                " or anyone else to know what “fred” is and what its purpose is.", "ConstructorFifth",
                null, null));
        library.put("ConstructorFifth", new Scenario(jc, Ruben, "The fourth part is the keyword new. This" +
                " lets us know that the next method is a constructor that will create a new instance of its class.",
                "ConstructorSixth", null, null));
        library.put("ConstructorSixth", new Scenario(jc, Ruben, "The fourth part is the keyword new. " +
                "This lets us know that the next method is a constructor that will create a new instance of its class.",
                "ConstructorSeventh", null, null));
        library.put("ConstructorSeventh", new Scenario(jc, Ruben, "The final part is “Donut(“Vanilla”);”." +
                " This is calling the constructor. Constructors also differ from other methods because its name is " +
                "the same as the class name. In this example, we are passing the value “Vanilla” into the " +
                "constructor. In the case of the Donut class, this means that our donut, called vanil, has vanilla" +
                " icing as soon as it is created. ", "String", null, (jc1) -> {
                    jc.getUser().setQualityTrue("Constructor");
        }));
        library.put("String", new Scenario(jc, Ruben, "If you are wondering what “Vanilla”, the object, " +
                "not the flavor, is, it is called a String. A String can be created simply by adding double quotes" +
                " around a sequence of characters. Any text you can think of can be made into a String. Other people" +
                " will go more into depth on the topic, but for now, think of it as a value for a variable.",
                "ConstructorPractice", null, (jc1) -> {
                    jc.getUser().setQualityTrue("String");
                    jc.getUser().setQualityTrue("Donut");
        }));
        library.put("ConstructorPractice", new Scenario(jc, Ruben, "I’ve been talking for quite a while, " +
                "so why don’t you try making your own Donut as practice? Your Codex should have all the relevant " +
                "information. By the way, classes can have multiple constructors that take different inputs. Try out" +
                " any of the constructors you find for the Donut class.", null, null,
                (jc1) -> {
                    jc.setHintMessage("The Donut tab should have all of the constructors for a Donut. Remember" +
                            " to follow the form: Class variable = new Class();");
                    jc.getUser().setQualityTrue("ConstructorPractice");
                }));
        library.put("MethodPractice", new Scenario(jc, Ruben, "Great. Now that we have a Donut, why don’t" +
                " we try to give it some filling? There should be a new method called “fill” in your Codex. The" +
                " “Methods” tab should help you out if you aren’t sure how to format it.", null,
                null, (jc1) -> {
                    jc.setHintMessage("Remember that to use an object, you need to follow the form: " +
                            "variable.method(input);");
                    jc.getUser().setQualityTrue("MethodPractice");
                }));
        library.put("CompleteDonut", new Scenario(jc, Ruben, "Now that we have a complete Donut, you can" +
                " view any of its variables with various \"get\" commands while you are looking at the environment" +
                " variables. API wanted to make it simpler to view the results of return statements early on.",
                "Primitives", null,
                (jc1) -> {
                    jc.setHintMessage(null);
                    jc.getUser().setQualityTrue("User");
                }));
        library.put("Primitives", new Scenario(jc, Ruben, "We took a basic look at Strings, but there " +
                "are several different types of data that you may end up using. The particular ones that we will " +
                "take a look at are  “short”, “int”, “long”, “double”, “float”, “char”, and “boolean”. These are " +
                "all called “primitive” data types. They are primitive not because they live in a cave, but becau" +
                "se they are just raw bits of data. This is compared to their Wrapper classes which have methods. " +
                "I believe you’ll learn about Wrapper classes when you meet the cats.", "Integers",
                null, null));
        library.put("Integers", new Scenario(jc, Ruben, "Let’s look at the types based on their " +
                "category. First is integers. A short is the smallest data type meant for integers. It can hold " +
                "up to 32,767 and down to -32,768. Seems pretty big, right? Well, some commands can require crazy" +
                " high numbers. This couldn’t even hold a 6 figure salary.", "IntegersSecond",
                null, null));
        library.put("IntegersSecond", new Scenario(jc, Ruben, "Next on our list is int. This one might " +
                "have been the most obvious. int tends to be the default data type for integers. An int can hold " +
                "up to 2,147,483,647 and down to -2,147,483,648. Surely this is big enough? Well, you probably " +
                "won’t need anything bigger than an int in most cases, but let’s take this to the extreme.",
                "IntegersFinal", null, null));
        library.put("IntegersFinal", new Scenario(jc, Ruben, "Last of the integers is long. A long can " +
                "hold up to 9,223,372,036,854,775,807 and down to -9,223,372,036,854,775,808. This is the largest " +
                "primitive type. Massive, right? People sometimes need even larger numbers, but that falls outside " +
                "of what we are covering on your journey.",
                "Decimals", null, null));
        library.put("Decimals", new Scenario(jc, Ruben, "The next category is decimals. Unlike integers, " +
                "the decimal data types are just concerned with decimal places instead of outright size. Our first " +
                "entry for this is the standard double. It only has two decimal places, so it is great for money" +
                " and percentages.", "DecimalsSecond", null, null));
        library.put("DecimalsSecond", new Scenario(jc, Ruben, "If two decimal places seem too few or " +
                "too many, then you might want to use a float. A float will allow for the largest number of " +
                "decimal places, but you will likely need to format it if you wish to display its information " +
                "as you intend. The large number of decimal places can lead to some strange looking output" +
                " depending on the context.", "Character", null, null));
        library.put("Character", new Scenario(jc, Ruben, "The next two categories only have one entry " +
                "each. The first of these categories is characters. Its entry is the simple char. This can be a " +
                "character like ‘a’ or a number. The number refers to a particular encoding of numbers to characters" +
                " called the ASCII code. It only goes up to 255. 65-90 are the uppercase letters, and 97-122 are" +
                " the lowercase letters. You can use a char as a number as well, so you could perform some math " +
                "to get certain characters.", "Boolean", null, null));
        library.put("Boolean", new Scenario(jc, Ruben, "The final category is boolean. Its entry is " +
                "simply boolean. Instead of being anything scary, it is just true or false. Booleans are very " +
                "important when it comes to any kind of conditional statement. For example, if someone was about" +
                " to fall over from exhaustion, it might not be the best time to suggest hitting the gym. The cats " +
                "and the ferrets will likely have a lot more to talk about when it comes to booleans.",
                "PrimitiveQuiz", null, null));
        library.put("PrimitiveQuiz", new Scenario(jc, Ruben, "Let’s have a little quiz. I’ll make some " +
                "buttons for you to choose from for each question. Click the button that you believe best fits as the" +
                "answer, Feel free to use your Codex, if you wish.", "Question1", null, (jc1) -> {
                    jc.getUser().setQualityTrue("Primitive");
                }));
        library.put("Question1", new Scenario(jc, Ruben, "Question 1. What type has the largest storage " +
                "space for integers?", null, null, (jc1) -> {
                    jc.setHintMessage("Remember to look through your Codex for information on these primitives. " +
                            "If you are not sure how to interact with the Buttons, then the Button tab should " +
                            "help you out.");
                    jc.getEnvironmentObjects().get("Bakery").put("short", new ButtonItem("\"short\"", jc));
                    Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                        ((JavaCast) var.get("JavaCast")).sayMessage("A short has the smallest storage space. Try agai" +
                                "n. What type has the largest storage space for integers?");
                    };
                    jc.getEnvironmentObjects().get("Bakery").get("short").setVariable("OnAction", newOnAction);
                    jc.getEnvironmentObjects().get("Bakery").put("long", new ButtonItem("\"long\"", jc));
                    newOnAction = (var) -> {
                        ((JavaCast) var.get("JavaCast")).sayMessage("Correct. Long has the largest storage " +
                                "space for integers.");
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("short");
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("float");
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("boolean");
                        ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("long");
                        ((JavaCast) var.get("JavaCast")).getTimer().schedule(
                                ((JavaCast) var.get("JavaCast")).getLibrary().get("Question2"), 5);
                    };
                    jc.getEnvironmentObjects().get("Bakery").get("long").setVariable("OnAction", newOnAction);
                    jc.getEnvironmentObjects().get("Bakery").put("float", new ButtonItem("\"float\"", jc));
                    newOnAction = (var) -> {
                        ((JavaCast) var.get("JavaCast")).sayMessage("Float is for decimals. Try again. " +
                            "What type has the largest storage space for integers?");
                    };
                    jc.getEnvironmentObjects().get("Bakery").get("float").setVariable("OnAction", newOnAction);
                    jc.getEnvironmentObjects().get("Bakery").put("boolean", new ButtonItem("\"boolean\"", jc));
                    newOnAction = (var) -> {
                        ((JavaCast) var.get("JavaCast")).sayMessage("Booleans only hold true or false. Try again. " +
                                "What type has the largest storage space for integers?");
                    };
                    jc.getEnvironmentObjects().get("Bakery").get("boolean").setVariable("OnAction", newOnAction);
                }));
        library.put("Question2", new Scenario(jc, Ruben, "Question 2. What type is used to " +
                "store letters?", null, null, (jc1) -> {
            jc.getEnvironmentObjects().get("Bakery").put("double", new ButtonItem("\"double\"", jc));
            Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("A double is for decimals. Try agai" +
                        "n. What type is used to store letters?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("double").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("char", new ButtonItem("\"char\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Correct. Chars are used to store letters.");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("double");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("char");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("boolean");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("int");
                ((JavaCast) var.get("JavaCast")).getTimer().schedule(
                        ((JavaCast) var.get("JavaCast")).getLibrary().get("Question3"), 5);
            };
            jc.getEnvironmentObjects().get("Bakery").get("char").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("int", new ButtonItem("\"int\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Int is for integers. Try again. " +
                        "What type is used to store letters?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("int").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("boolean", new ButtonItem("\"boolean\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Booleans only hold true or false. Try again. " +
                        "What type is used to store letters?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("boolean").setVariable("OnAction", newOnAction);
        }));
        library.put("Question3", new Scenario(jc, Ruben, "Question 3. What type is important for " +
                "conditional statements?", null, null, (jc1) -> {
            jc.getEnvironmentObjects().get("Bakery").put("long", new ButtonItem("\"long\"", jc));
            Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("A long is used for integers. Try agai" +
                        "n. What type is important for conditional statements?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("long").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("boolean", new ButtonItem("\"boolean\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Correct. Booleans store true or false, so they are " +
                        "important for conditional statements.");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("long");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("short");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("boolean");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("char");
                ((JavaCast) var.get("JavaCast")).getTimer().schedule(
                        ((JavaCast) var.get("JavaCast")).getLibrary().get("Question4"), 5);
            };
            jc.getEnvironmentObjects().get("Bakery").get("boolean").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("short", new ButtonItem("\"short\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Shorts are for integers. Try again. " +
                        "What type is important for conditional statements?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("short").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("char", new ButtonItem("\"char\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Chars are for single characters. Try again. " +
                        "What type is important for conditional statements?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("char").setVariable("OnAction", newOnAction);
        }));
        library.put("Question4", new Scenario(jc, Ruben, "Question 4. What is the most commonly used type" +
                " for integers?", null, null, (jc1) -> {
            jc.getEnvironmentObjects().get("Bakery").put("long", new ButtonItem("\"long\"", jc));
            Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("A long is quite large. It would be wasteful to " +
                        "default to such a large structure. Try again. What is the most commonly used type " +
                        "for integers?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("long").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("int", new ButtonItem("\"int\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Correct. Ints are neither too big or too small which" +
                        " makes it a good default way to store integers.");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("long");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("short");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("int");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("char");
                ((JavaCast) var.get("JavaCast")).getTimer().schedule(
                        ((JavaCast) var.get("JavaCast")).getLibrary().get("Question5"), 5);
            };
            jc.getEnvironmentObjects().get("Bakery").get("int").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("short", new ButtonItem("\"short\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Shorts are fairly small. It would be too easy to" +
                        " accidentally go over the limit. Try again. What is the most commonly used type for " +
                        "integers?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("short").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("char", new ButtonItem("\"char\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Chars are for single characters. Try again. " +
                        "What is the most commonly used type for integers?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("char").setVariable("OnAction", newOnAction);
        }));
        library.put("Question5", new Scenario(jc, Ruben, "Question 5. What is the most commonly used type " +
                "for decimals?", null, null, (jc1) -> {
            jc.getEnvironmentObjects().get("Bakery").put("float", new ButtonItem("\"float\"", jc));
            Consumer<HashMap<String, Object>> newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("A float lets you have several decimal places for precis" +
                        "ion, but it becomes rather bulky if you just want some percentages. Try again. " +
                        "What is the most commonly used type for decimals?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("float").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("double", new ButtonItem("\"double\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Correct. Double is the typical primitive used for" +
                        " decimals since percentages and currency are more common in general usage.");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("float");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("double");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("int");
                ((JavaCast) var.get("JavaCast")).getEnvironmentObjects().get("Bakery").remove("char");
                ((JavaCast) var.get("JavaCast")).getTimer().schedule(
                        ((JavaCast) var.get("JavaCast")).getLibrary().get("PrimitiveQuizFinish"), 5);
            };
            jc.getEnvironmentObjects().get("Bakery").get("double").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("int", new ButtonItem("\"int\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Ints are used for integers. Try again. " +
                        "What is the most commonly used type for decimals?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("int").setVariable("OnAction", newOnAction);
            jc.getEnvironmentObjects().get("Bakery").put("char", new ButtonItem("\"char\"", jc));
            newOnAction = (var) -> {
                ((JavaCast) var.get("JavaCast")).sayMessage("Chars are for single characters. Try again. " +
                        "What is the most commonly used type for decimals?");
            };
            jc.getEnvironmentObjects().get("Bakery").get("char").setVariable("OnAction", newOnAction);
        }));
        library.put("PrimitiveQuizFinish", new Scenario(jc, Ruben, "Well done. Let’s move onto our final " +
                "topic: arrays.", "Arrays", null, (jc1) -> {
                    jc.setHintMessage(null);
        }));
        library.put("Arrays", new Scenario(jc, Ruben, "The kind of arrays we’ll be discussing are " +
                "primitive arrays. Primitive arrays are like our primitive data types, they don’t have " +
                "their own methods. They aren’t restricted to using primitive data types, so our example " +
                "will be using Donuts.", "ArrayInitialize", null, null));
        library.put("ArrayInitialize", new Scenario(jc, Ruben, "If I were to make an array of Donuts, " +
                "I would use the command\n" +
                "Donut[ ] tray = new Donut[ 5 ];\n" +
                "Adding [ ] to the end of the class makes it into an array of that class, thus I have created an " +
                "array of Donuts called tray. Because I put 5 into the square brackets, which is different from " +
                "the usual parenthesis, that means that tray can hold onto 5 Donuts.", "ArrayAccess",
                null, null));
        library.put("ArrayAccess", new Scenario(jc, Ruben, "The elements of the array must be accessed " +
                "in a particular way. If I want to get the first element, I have to include tray[0] in my command. " +
                "Arrays start at zero, so always remember the position of the final element will always be one less " +
                "than the total number of elements.", "ArrayInsert",
                null, null));
        library.put("ArrayInsert", new Scenario(jc, Ruben, "Right now, there is nothing in this array. It is just a series of placeholders waiting for an actual Donut. I’m going to make a chocolate Donut the first element. Here is my command:\n" +
                "tray[0] = new Donut(“Chocolate”);\n" +
                "This means that tray[0] is now a fresh chocolate Donut. Go ahead and give it a filling.\n",
                null, null, (jc1) -> {
            jc.getEnvironmentObjects().get("Bakery").put("tray[0]", new DonutItem("\"Chocolate\"", jc));
            jc.getEnvironmentObjects().get("Bakery").put("tray[1]", new DonutItem(new Null()));
            jc.getEnvironmentObjects().get("Bakery").put("tray[2]", new DonutItem(new Null()));
            jc.getEnvironmentObjects().get("Bakery").put("tray[3]", new DonutItem(new Null()));
            jc.getEnvironmentObjects().get("Bakery").put("tray[4]", new DonutItem(new Null()));
            jc.getUser().setQualityTrue("ArrayInsertPractice");
        }));
        library.put("ArrayRemove", new Scenario(jc, Ruben, "I don’t want to remove your hard work so I’ll " +
                "save your Donut outside of the array. First, I’ll issue the command\n" +
                "Donut masterpiece = tray[0];\n" +
                "then, I’ll issue the command\n" +
                "tray[0] = null;", "ArrayNull",
                null, (jc1) -> {
            jc.getEnvironmentObjects().get("Bakery").put("masterpiece", new DonutItem((DonutItem)
                    jc.getEnvironmentObjects().get("Bakery").get("tray[0]")));
            jc.getEnvironmentObjects().get("Bakery").put("tray[0]", new DonutItem(new Null()));
        }));
        library.put("ArrayNull", new Scenario(jc, Ruben, "Null is a keyword that means nothing, as in the" +
                " void sort of nothing. The second command is the way to remove elements from an array. You can also" +
                " replace an element with a new one without needing to remove the old one.", "BakeryFinal",
                null, (jc1) -> {
                    jc.getUser().setQualityTrue("Arrays");
        }));
        library.put("BakeryFinal", new Scenario(jc, Ruben, "That’s all I had for you. Feel free to " +
                "experiment some more. I need to finally put these cookies in the oven " +
                "and prep for some business tomorrow. From here you should be able to get to the dog’s house up " +
                "north, the cats down south, or the snails to the east.", null,
                null, (jc1) -> {
            jc.setHintMessage("There's multiple paths, but I think they are all closed besides the path to the" +
                    " Village. Remember to look at your map if you haven't already.");
            jc.getUser().setQualityTrue("Bakery");
            jc.getUser().setQualityTrue("MushroomVillageKnown");
            jc.getTarget().setVisible(false);
        }));
        library.put("Village", new Scenario(jc, Treble, "*Music can be heard from one of the houses*", "DemoEnd", null, (jc1) -> {
            jc.setHintMessage(null);
            jc.getWorldMusic().play();
            jc.familiarSays("I'm not seeing anyone around here, boss, but there sure is a lot of noise coming from" +
                    " that house up ahead.");
        }));
        library.put("DemoEnd", new Scenario(jc, Treble, "Oh, hey there. Unfortunately, this is the end" +
                " of the Demo so far. Feel free to try out the things in your Codex. More work will be getting " +
                "done over the Summer. Some of the possibilities planned are a bit complex and may cause some " +
                "serious delays to figure them out. Thank you for trying out this game.", null,
                null, (jc1) -> {
            jc.setHintMessage("Thanks for hanging out with me boss. I hope to see you once the roads have opened up.");
            jc.getTarget().setVisible(true);
        }));
        library.put("Bet", new Scenario(jc, Treble, "Ha. I win. It was today. Clef owes me some enoki now.",
                "BasicStageSlider", null, (jc1) -> {
            jc.getTarget().setVisible(true);
        }));
        library.put("BasicStageSlider", new Scenario(jc, Treble, "My name is Treble. I’m one of the " +
                "inhabitants of this village. Most of the rest are partying right now. I’m sure you can hear " +
                "their music from here. Speaking of which, I’ll be teaching you about music and Buttons.",
                "BasicStageSlider2", null, null));

        }

    public HashMap<String, Scenario> getLibrary() {
        return library;
    }
}
