package cn.hellobike.hippo.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/25 20:48
 * @Description:
 */
public class AddField extends ClassVisitor {

	private String name;
	private int access;
	private String desc;
	private Object value;

	private boolean duplicate;

	// 构造器
	public AddField(ClassVisitor cv, String name, int access, String desc, Object value) {
		super(ASM5, cv);
		this.name = name;
		this.access = access;
		this.desc = desc;
		this.value = value;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		if (this.name.equals(name)) {
			duplicate = true;
		}
		return super.visitField(access, name, desc, signature, value);
	}

	@Override
	public void visitEnd() {
		if (!duplicate) {
			FieldVisitor fv = super.visitField(access, name, desc, null, value);
			if (fv != null) {
				fv.visitEnd();
			}
		}
		super.visitEnd();
	}
}