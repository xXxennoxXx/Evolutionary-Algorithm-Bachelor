package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
	File file;
	public int mutateChance;
	public int crossChance;
	ArrayList<Integer> choice = new ArrayList<Integer>();
	ArrayList<Double> prob = new ArrayList<Double>();
	ArrayList<String> def = new ArrayList<String>();
	ArrayList<Point> listOfPoint = new ArrayList<Point>();
	ArrayList<ParametrPoint> listOfParametrs = new ArrayList<ParametrPoint>();
	Integer[][] zadanie = { { 10, 1 }, { 20, 1 }, { 50, 1 }, { 100, 1 }, { 200, 1 }, { 500, 1 }, };




}
