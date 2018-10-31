package com.wzw.work.util;

import java.io.File;

/**
* @Description:  文件删除工具类
* @Param:
* @return:
* @Author: wuzhangwei
* @Date: 18-10-31 下午1:49
*/
public class DeleteFileUtil {
    public static void deleteDataAndFile(String fileUrl) {
		try {
			File file = new File(fileUrl);
			if (file.exists()) {
				if (forceDelete(file))
					System.out.println("附件文件" + fileUrl + "删除成功!");
			}
		} catch (Exception ex) {
			ex.fillInStackTrace();
		}
	}

	// Java 中强制删除文件的方法
	private static boolean forceDelete(File f) {
		boolean result = false;
		int tryCount = 0;
		while (!result && tryCount++ < 10) {
			System.out.println("附件文件try to delete file " + f.getName()
					+ " 删除个数:" + tryCount);
			System.gc();
			result = f.delete();
		}

		return result;
	}


	/**
	 * @Description: 删除空目录
	 *
	 * @param
	 * @author Created by wuzhangwei on 18-10-31 下午1:49
	 */

	public static void doDeleteEmptyDir(String dir) {
		boolean success = (new File(dir)).delete();
		if (success) {
			System.out.println("Successfully deleted empty directory: " + dir);
		} else {
			System.out.println("Failed to delete empty directory: " + dir);
		}
	}

	/**
	 * @Description: 递归删除目录下的所有文件及子目录下所有文件
	 *
	 * @param dir 将要删除的文件目录
	 * @author Created by wuzhangwei on 18-10-31 下午1:50
	 */

	public static boolean deleteDir(File dir) {
		try {
			if (dir.isDirectory()) {
				System.out.println("删除附件文件目录" + dir.getName());
				System.gc();
				String[] children = dir.list();
				// 递归删除目录中的子目录下
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteDir(new File(dir, children[i]));
					if (!success) {
						return false;
					}
				}
			}
			// 目录此时为空，可以删除
			/*		Java中删除文件、删除目录及目录下所有文件
			功能：删除某个目录及目录下的所有子目录和文件
			知识点：File.delete()用于删除“某个文件或者空目录”！所以要删除某个目录及其中的所有文件和子目录，要进行递归删除，具体代码示例如下：*/
			return dir.delete();
		} catch (Exception ex) {
			ex.fillInStackTrace();
			return false;
		}
	}

	/**
	 *测试
	 */
	public static void main(String[] args) {
		doDeleteEmptyDir("new_dir1");
		String newDir2 = "new_dir2";
		boolean success = deleteDir(new File(newDir2));
		if (success) {
			System.out.println("Successfully deleted populated directory: "
					+ newDir2);
		} else {
			System.out.println("Failed to delete populated directory: "
					+ newDir2);
		}
	}

}