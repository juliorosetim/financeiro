import {Cartao} from '@/type/CartaoType';
import axios from 'axios';
import { RemoteError, Response } from './Rest';

class CartaoService {

  public async findAllCartoes(): Promise<Response<Cartao>> {
    const response = new Response<Cartao>();

    try {
      const  data  = await axios.get('http://localhost:8081/api/cartao')
      console.log(`data ${data}`)
      response.content = data.data;
    } catch (e) {
      response.error = this.remoteError(e);
    }

    return response;
  }


  private remoteError(error: any): RemoteError {
    return {
      message: error.response.data.message,
      code: error.response.data.status
    } as RemoteError;
  }  

}

export default new CartaoService();