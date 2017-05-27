using System.Collections.Generic;
using System.ServiceModel;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.SL.ServiceLayerContracts
{
    [ServiceContract]
    public interface ICategoryService
    {
        [OperationContract]
        IEnumerable<CategoryDTO> GetAll();
    }
}
