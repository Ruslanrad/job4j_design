package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

class BoxTest {
    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTypeVertexThreeUnknown(){
        Box box = new Box(3, 3);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Unknown object");
    }

    @Test
    void isVertexIsTwo(){
        Box box = new Box(4, 10);
        int resul = box.getNumberOfVertices();
        assertThat(resul).isEqualTo(4);
    }

    @Test
    void isVertexIsTen(){
        Box box = new Box(10, 10);
        int resul = box.getNumberOfVertices();
        assertThat(resul).isEqualTo(-1);
    }

    @Test
    void isEdgeIsNotPositive(){
        Box box = new Box(10, -10);
        int resul = box.getNumberOfVertices();
        assertThat(resul).isEqualTo(-1);
    }

    @Test
    void isThisAreaCube(){
        Box box = new Box(8, 3);
        double result = box.getArea();
        double excepted = 54.0;
        assertThat(result).isCloseTo(excepted, withinPercentage(0.1));

    }

    @Test
    void isThisAreaSphere(){
        Box box = new Box(0, 3);
        double result = box.getArea();
        assertThat(result).isCloseTo(113.097, withinPercentage(0.005));

    }

    @Test
    void isExistIsFalse(){
        Box box = new Box(10, 3);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void isExistIsTrue(){
        Box box = new Box(4, 3);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }
}