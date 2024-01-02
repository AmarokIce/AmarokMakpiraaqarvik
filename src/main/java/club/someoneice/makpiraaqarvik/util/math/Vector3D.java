package club.someoneice.makpiraaqarvik.util.math;

import java.util.Objects;

public class Vector3D {
    public Double x;
    public Double y;
    public Double z;

    public Vector3D(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);

    public Vector3D vectorTo(Vector3D vec3d) {
        Vector3D ret = Vector3D.ZERO;
        ret.x = vec3d.x - this.x;
        ret.y = vec3d.y - this.y;
        ret.z = vec3d.z - this.z;
        return ret;
    }

    public Vector3D cross(Vector3D vec3d) {
        Vector3D ret = Vector3D.ZERO;
        ret.x = this.y * vec3d.z - this.z * vec3d.y;
        ret.y = this.z * vec3d.x - this.x * vec3d.z;
        ret.z = this.x * vec3d.y - this.y * vec3d.x;
        return ret;
    }

    public void add(Vector3D vec3d) {
        add(vec3d.x, vec3d.y, vec3d.z);
    }

    public void add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void sub(double x, double y, double z) {
        add(-x, -y, -z);
    }

    public void sub(Vector3D vec3d) {
        sub(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3D vector3D = (Vector3D) o;
        return Objects.equals(x, vector3D.x) && Objects.equals(y, vector3D.y) && Objects.equals(z, vector3D.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
