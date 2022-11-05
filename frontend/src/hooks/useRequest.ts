import { useCallback, useState } from "react";

interface useRequestReturn<T> {
  request: (fetchRequest: () => Promise<T>) => Promise<void>;
  data: T | undefined;
  isLoading: boolean;
  error: unknown;
}

const useRequest = <T>(): useRequestReturn<T> => {
  const [data, setData] = useState<T>();
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<unknown>();

  const request = useCallback(async (fetchRequest: () => Promise<T>) => {
    setIsLoading(true);
    try {
      let response = await fetchRequest();
      setData(response);
    } catch (e) {
      setError(e);
    } finally {
      setIsLoading(false);
    }
  }, []);
  return { request, data, isLoading, error };
};

export default useRequest;
