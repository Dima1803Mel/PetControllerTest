package utils;

import com.github.javafaker.Faker;
import models.pet.Category;
import models.pet.Pet;
import models.pet.Status;
import models.pet.Tag;

import java.util.Arrays;

public class DataGenerator {
    private static final Faker datafaker = new Faker();

    public static Pet generateFullDataPet() {
        return Pet
                .builder()
                .id(getRandom())
                .name(datafaker.funnyName().name())
                .photoUrls(Arrays.asList(datafaker.animal().name(), datafaker.animal().name()))
                .tags(
                        Arrays.asList(
                                Tag
                                        .builder()
                                        .id(getRandom())
                                        .name(datafaker.funnyName().name())
                                        .build()
                        )
                )
                .category(
                        Category.builder()
                                .name(datafaker.funnyName().name())
                                .id(getRandom())
                                .build()
                )
                .status(Status.sold)
                .build();
    }

    private static int getRandom() {
        return datafaker.number().numberBetween(10, 10000);
    }
}
