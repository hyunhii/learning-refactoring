package book.chapter10;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application10_4 {
    public static void main(String[] args) {
        List<Bird> birds = createSampleBirds();

        System.out.println("plumages(birds) = " + plumages(birds));
        System.out.println("speeds(birds) = " + speeds(birds));
    }

    private static List<Bird> createSampleBirds() {
        return List.of(new Bird("유럽 제비"), new Bird("아프리카 제비"), new Bird("노르웨이 파랑 앵무"), new Bird("없는종"));
    }

    public static Map<String, String> plumages(List<Bird> birds) {
        return birds.stream().collect(Collectors.toMap(bird -> bird.type, bird -> plumage(bird)));
    }

    public static Map<String, Integer> speeds(List<Bird> birds) {
        return birds.stream()
                .collect(Collectors.toMap(bird -> bird.type, bird -> airSpeedVelocity(bird)));
    }

    public static String plumage(Bird bird) {
        switch (bird.type) {
            case "유럽 제비":
                return "보통이다.";
            case "아프리카 제비":
                return bird.numberOfCoconuts > 2 ? "지쳤다." : "보통이다.";
            case "노르웨이 파랑 앵무":
                return bird.voltage > 100 ? "그을렸다." : "예쁘다.";
            default:
                return "알 수 없다.";
        }
    }

    public static int airSpeedVelocity(Bird bird) {
        switch (bird.type) {
            case "유럽 제비":
                return 35;
            case "아프리카 제비":
                return 40 - 2 * bird.numberOfCoconuts;
            case "노르웨이 파랑 앵무":
                return bird.isNailed ? 0 : 10 + bird.voltage / 10;
            default:
                return 0;
        }
    }
}

@Data
@RequiredArgsConstructor
class Bird {
    public final String type;
    public int numberOfCoconuts;
    public int voltage;
    public boolean isNailed;
}
