export type RemoteError = {
    message: string;
    code?: number;
    type?: string;
  };

export class Response<T> {
    content?: T;
    error?: RemoteError;
    static error: RemoteError;
  
    get hasError(): boolean {
      return this.error !== undefined;
    }
  }