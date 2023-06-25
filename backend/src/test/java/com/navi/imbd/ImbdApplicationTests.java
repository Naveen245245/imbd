package com.navi.imbd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.navi.imbd.ImbdApplicationTests.countStringNumber;

@SpringBootTest
class ImbdApplicationTests {
	public static void main(String[] args) {
		String str= "Naveen Naveen Sunitha Sunitha";
		ImbdApplicationTests.countStringNumber(str);
	}
	public static void countStringNumber(String str){
		Map<String,Integer> stringCountMap = new HashMap<>();
		String [] stringlist = str.split(" ");
		for(String s:stringlist){
			if(!stringCountMap.containsKey(s)){
				stringCountMap.put(s,1);
			}else{
				stringCountMap.put(s,1+1);
			}
		}

		Map<String, Integer> stringCountMap1 = stringCountMap;
		for (Map.Entry<String, Integer> entries:stringCountMap.entrySet()) {
			System.out.print(entries.getKey() +" "+entries.getValue());
		}


	}

}
