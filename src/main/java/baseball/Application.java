package baseball;
import camp.nextstep.edu.missionutils.*;
import java.util.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Application {
    public static List<Integer> makeNum(){
        List<Integer> computer = new ArrayList<>();
        while (computer.size()<3){
            int randomNumber = Randoms.pickNumberInRange(1, 8);
            if (!computer.contains(randomNumber)){
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    public static void findError(String num) throws IllegalArgumentException{

        if (num.length() != 3) {
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        } else {System.out.println("올바른 입력입니다.");}
    }
    public static int[] readNum(){
        System.out.print("숫자를 입력해주세요 : ");
        String input=Console.readLine();
        findError(input);
        if (input.length()!=3 && input.contains("0")){
            throw new IllegalArgumentException("잘못된 숫자입니다.");
        }

        return Stream.of(input.split("")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] score(List<Integer> computer, int[] arrNum){
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 3; i++) {
            if (computer.get(i) == arrNum[i]) {
                strike += 1;
            } else if (computer.contains(arrNum[i])) {
                ball += 1;
            }
        }
        int[] result={strike,ball};
        return result;
    }

    public static void result(int[] result){
        if (result[0]==0 && result[1]==0){
            System.out.println("낫싱");
        } else if (result[0]==0) {
            System.out.println(result[1]+"볼");
        } else if (result[1]==0) {
            System.out.println(result[0]+"스트라이크");
        } else {
            System.out.println(result[1]+"볼 "+result[0]+"스트라이크");
        }
    }

    public static List<Integer> restart(int result, List<Integer> computer){
        if (result==1){
            return makeNum();
        }
        return computer;
    }

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현7
        int start=1;

        List<Integer> computer = makeNum();
        for (Integer data : computer) {
            System.out.print(data);
        }

        while(start==1) {

            int[] arrNum = readNum();
            int[] result=score(computer, arrNum);
            result(result);

            if (result[0] == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                start = Integer.parseInt(Console.readLine());
                computer = restart(start, computer);
            }
        }
    }
}