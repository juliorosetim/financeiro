import Usuario from '@/type/usuarioType';
import axios from 'axios';
import { RemoteError, Response } from './Rest';

class CadastroUsuarioService {

  public async findAllUsuarios(): Promise<Response<Usuario>> {
    const response = new Response<Usuario>();

    try {
      const { data } = await axios.get('http://localhost:8081/api/usuario')
      response.content = data;
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

export default new CadastroUsuarioService();