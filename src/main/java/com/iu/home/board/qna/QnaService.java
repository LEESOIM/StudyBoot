package com.iu.home.board.qna;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.home.util.FileManager;
import com.iu.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j //Logger객체 생성 역할
@Transactional(rollbackFor = Exception.class) //Exception이 발생했을때 rollback
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	@Autowired
	private FileManager fileManager;
	
	
	@Value("${app.upload.qna}") //프로퍼티스 값을 가져오는 어노테이션
	private String path;
	
	public String setSummerFile(MultipartFile file) throws Exception {
		String fileName = fileManager.saveFile(file, path);
		fileName = "/file/qna/"+fileName;
		return fileName;
	}
	
	public boolean SetSummerFileDelete(String fileName) throws Exception {
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		log.info("fileName => {}",fileName);
		File file = new File(path, fileName);
		return file.delete();
	}
	
	public List<QnaVO> getList(Pager pager) throws Exception{
		pager.makeRow();
		return qnaMapper.getList(pager);
	}
	
	public int setAdd(QnaVO qnaVO) throws Exception{
		int result = qnaMapper.setAdd(qnaVO);
		
		File file = new File(path);
		if(!file.exists()) {
			boolean check = file.mkdirs();
		}
		
		if(qnaVO.getFiles()!=null) {
			for(MultipartFile f : qnaVO.getFiles()) {
				if(!f.isEmpty()) {
					log.info("fileName : {}", f.getOriginalFilename());
					String fileName = fileManager.saveFile(f, path);
					QnaFileVO qnaFileVO = new QnaFileVO();
					qnaFileVO.setFileName(fileName);
					qnaFileVO.setOriName(f.getOriginalFilename());
					qnaFileVO.setNum(qnaVO.getNum());
					
					qnaMapper.setFileAdd(qnaFileVO);
				}
			}
		}
		
		return result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception {
		return qnaMapper.getFileDetail(qnaFileVO);
	}
	
	public int setUpdate(QnaVO qnaVO) throws Exception {
		int result = qnaMapper.setUpdate(qnaVO);
		
		File file = new File(path);
		if(!file.exists()) {
			boolean check = file.mkdirs();
		}
		
		for(MultipartFile f : qnaVO.getFiles()) {
				
			if(!f.isEmpty()) {
				log.info("fileName : {}", f.getOriginalFilename());
				String fileName = fileManager.saveFile(f, path);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(f.getOriginalFilename());
				qnaFileVO.setNum(qnaVO.getNum());
				
				qnaMapper.setFileAdd(qnaFileVO);
			}
		}
		return result;
	}
	
	public int setFileDelete(QnaFileVO qnaFileVO) throws Exception {
		qnaFileVO = qnaMapper.getFileDetail(qnaFileVO);
		int result = qnaMapper.setFileDelete(qnaFileVO);
		if(result > 0) {
			File file = new File(path, qnaFileVO.getFileName());
			file.delete();
		}
		return result;
	}
}
