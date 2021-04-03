package com.example.study.ifs;

import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;

// 모든 Controller가 동일한 Interface를 상속 받을 수 있도록 Generic으로 정의 함 ( Req, Res로 선언하여 매핑 )
public interface CrudInterface<Req,Res> {

    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
